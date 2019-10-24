package uo.ri.ui.cashier.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.invoice.InvoiceService;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

import java.util.ArrayList;
import java.util.List;

public class WorkOrderBillingAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> workOrderIds = new ArrayList<Long>();

		// type work order ids to be invoiced in the invoice
		do {
			Long id = Console.readLong("Type work order ids ? ");
			workOrderIds.add(id);
		} while (nextWorkorder());
		
		InvoiceService in = ServiceFactory.getInvoiceService();
		
		InvoiceDto invoice = in.createInvoiceFor(workOrderIds);
		Printer.printInvoice(invoice);

	}

	private boolean nextWorkorder() {
		return Console.readString(" Any other workorder? (y/n) ").equalsIgnoreCase("y");
	}

}
