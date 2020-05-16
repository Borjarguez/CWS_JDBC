package uo.ri.persistence.invoice.impl;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.invoice.InvoiceGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvoiceGatewayImpl implements InvoiceGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public long add(InvoiceDto in) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_INVOICE");
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, in.number);
			pst.setDate(2, new java.sql.Date(in.date.getTime()));
			pst.setDouble(3, in.vat);
			pst.setDouble(4, in.total);
			pst.setString(5, "NOT_YET_PAID");

			pst.executeUpdate();

			return getGeneratedKey(in.number); // New invoice id

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void testRepairs(List<Long> workOrderIDS) throws BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_CHECK_WORKORDER_STATUS");

		try {
			pst = c.prepareStatement(SQL);

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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public Long generateInvoiceNumber() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_LAST_INVOICE_NUMBER");

		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1;
			} else {
				return 1L;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public long getGeneratedKey(long numberInvoice) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_RETRIEVE_GENERATED_KEY");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, numberInvoice);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void updateWorkorderTotal(Long workOrderID, double total) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_AMOUNT");
		try {
			pst = c.prepareStatement(SQL);
			pst.setDouble(1, total);
			pst.setLong(2, workOrderID);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public double checkTotalParts(Long workOrderID) throws BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_PARTS_TOTAL");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0;
			}

			return rs.getDouble(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void updateWorkOrderStatus(List<Long> breakdownIds, String status) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_STATUS");

		try {
			pst = c.prepareStatement(SQL);

			for (Long breakdownId : breakdownIds) {
				pst.setString(1, status);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_WORKORDER_INVOICE_ASSOC");
		
		try {
			pst = c.prepareStatement(SQL);

			for (Long breakdownId : workOrderIDS) {
				pst.setLong(1, invoiceId);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public double checkTotalLabor(Long workOrderID) throws BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_LABOR_TOTAL");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("Workorder does not exist or it can not be charged");
			}

			return rs.getDouble(1);

		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
