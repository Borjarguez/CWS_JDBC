package uo.ri.business.TransactionScripts.casher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;

public class WorkOrderBilling {
	private List<Long> workOrderIds;
	private Connection con;

	private static final String SQL_CHECK_WORKORDER_STATUS = "select status from TWorkOrders where id = ?";

	private static final String SQL_LAST_INVOICE_NUMBER = "select max(invoice_number) from TInvoices";

	private static final String SQL_PARTS_TOTAL = "select sum(s.quantity * r.price) "
			+ "	from  TSubstitutions s, TSpareParts r " + "	where s.sparepart_id = r.id " + "		and s.labor_id = ?";

	private static final String SQL_LABOR_TOTAL = "select sum(i.minutes * tv.pricePerHour / 60) "
			+ "	from TWorkOrders a, TLabors i, TVehicles v, TVehicleTypes tv" + "	where i.workorder_id = a.id "
			+ "		and a.vehicle_id = v.id" + "		and v.vehicletype_id = tv.id" + "		and a.id = ?"
			+ "		and a.status = ''";

	private static final String SQL_UPDATE_WORKORDER_AMOUNT = "update TWorkOrders set amount = ? where id = ?";

	private static final String SQL_INSERT_INVOICE = "insert into TInvoices(invoice_number, invoice_date, vat, amount, status) "
			+ "	values(?, ?, ?, ?, ?)";

	private static final String SQL_WORKORDER_INVOICE_ASSOC = "update TWorkOrders set invoice_id = ? where id = ?";

	private static final String SQL_UPDATE_WORKORDER_STATUS = "update TWorkOrders set status = ? where id = ?";

	private static final String SQL_RETRIEVE_GENERATED_KEY = "select id from TInvoices where invoice_number = ?";

	public WorkOrderBilling(List<Long> workOrders) {
		this.workOrderIds = workOrders;
	}

	public InvoiceDto execute() throws BusinessException {

		try {
			con = Jdbc.getConnection();
			con.setAutoCommit(false);

			testRepairs(workOrderIds);

			InvoiceDto in = new InvoiceDto();

			in.number = generateInvoiceNumber();
			in.date =  Dates.today();
			double amount = calculateTotalInvoice(workOrderIds); // not vat included
			in.vat = vatPercentage(amount, in.date);
			in.total = Round.twoCents(amount * (1 + in.vat / 100));
			
			in.id = createInvoice(in);
			linkWorkorderInvoice(in.id, workOrderIds);
			updateWorkOrderStatus(workOrderIds, "INVOICED");
			
			con.commit();
			
			return in;
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(con);
		}
	}

	private void testRepairs(List<Long> workOrderIDS) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL_CHECK_WORKORDER_STATUS);

			for (Long workOrderID : workOrderIDS) {
				pst.setLong(1, workOrderID);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("Workorder " + workOrderID + " doesn't exist");
				}

				String status = rs.getString(1);
				if (!"FINISHED".equalsIgnoreCase(status)) {
					throw new BusinessException("Workorder " + workOrderID + " is not finished yet");
				}

			}
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private Long generateInvoiceNumber() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL_LAST_INVOICE_NUMBER);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1;
			} else {
				return 1L;
			}
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	protected double calculateTotalInvoice(List<Long> workOrderIDS) throws BusinessException, SQLException {

		double totalInvoice = 0.0;
		for (Long workOrderID : workOrderIDS) {
			double laborTotal = checkTotalLabor(workOrderID);
			double sparesTotal = checkTotalParts(workOrderID);
			double workTotal = laborTotal + sparesTotal;

			updateWorkorderTotal(workOrderID, workTotal);

			totalInvoice += workTotal;
		}
		return totalInvoice;
	}

	private void updateWorkorderTotal(Long workOrderID, double total) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(SQL_UPDATE_WORKORDER_AMOUNT);
			pst.setDouble(1, total);
			pst.setLong(2, workOrderID);
			pst.executeUpdate();
		} finally {
			Jdbc.close(pst);
		}
	}

	private double checkTotalParts(Long workOrderID) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL_PARTS_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0;
			}

			return rs.getDouble(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private double checkTotalLabor(Long workOrderID) throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL_LABOR_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("Workorder does not exist or it can not be charged");
			}

			return rs.getDouble(1);

		} catch (BusinessException e) {
			throw e;
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private void updateWorkOrderStatus(List<Long> breakdownIds, String status) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_UPDATE_WORKORDER_STATUS);

			for (Long breakdownId : breakdownIds) {
				pst.setString(1, status);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_WORKORDER_INVOICE_ASSOC);

			for (Long breakdownId : workOrderIDS) {
				pst.setLong(1, invoiceId);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private long createInvoice(InvoiceDto in) throws SQLException {

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(SQL_INSERT_INVOICE);
			pst.setLong(1, in.number);
			pst.setDate(2, new java.sql.Date(in.date.getTime()));
			pst.setDouble(3, in.vat);
			pst.setDouble(4, in.total);
			pst.setString(5, "NOT_YET_PAID");

			pst.executeUpdate();

			return getGeneratedKey(in.number); // New invoice id

		} finally {
			Jdbc.close(pst);
		}
	}

	private long getGeneratedKey(long numberInvoice) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL_RETRIEVE_GENERATED_KEY);
			pst.setLong(1, numberInvoice);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private double vatPercentage(double totalInvoice, Date dateInvoice) {
		return Dates.fromString("1/7/2012").before(dateInvoice) ? 21.0 : 18.0;
	}

}