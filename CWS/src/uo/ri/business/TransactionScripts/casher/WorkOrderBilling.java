package uo.ri.business.TransactionScripts.casher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.invoice.InvoiceGateway;

public class WorkOrderBilling {
	private List<Long> workOrderIds;

	public WorkOrderBilling(List<Long> workOrders) {
		this.workOrderIds = workOrders;
	}

	public InvoiceDto execute() throws BusinessException {
		Connection con = null;
		try {
			con = Jdbc.getConnection();
			con.setAutoCommit(false);
			
			InvoiceGateway ig = PersistenceFactory.getInvoiceGateway();

			ig.testRepairs(workOrderIds);

			InvoiceDto in = new InvoiceDto();

			in.number = ig.generateInvoiceNumber();
			in.date =  Dates.today();
			double amount = calculateTotalInvoice(ig, workOrderIds); // not vat included
			in.vat = vatPercentage(amount, in.date);
			in.total = Round.twoCents(amount * (1 + in.vat / 100));
			
			in.id = ig.add(in);
			ig.linkWorkorderInvoice(in.id, workOrderIds);
			ig.updateWorkOrderStatus(workOrderIds, "INVOICED");
			
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

	protected double calculateTotalInvoice(InvoiceGateway ig, List<Long> workOrderIDS) throws BusinessException, SQLException {
		double totalInvoice = 0.0;
		for (Long workOrderID : workOrderIDS) {
			double laborTotal = ig.checkTotalLabor(workOrderID);
			double sparesTotal = ig.checkTotalParts(workOrderID);
			double workTotal = laborTotal + sparesTotal;

			ig.updateWorkorderTotal(workOrderID, workTotal);

			totalInvoice += workTotal;
		}
		return totalInvoice;
	}


	private double vatPercentage(double totalInvoice, Date dateInvoice) {
		return Dates.fromString("1/7/2012").before(dateInvoice) ? 21.0 : 18.0;
	}

}
