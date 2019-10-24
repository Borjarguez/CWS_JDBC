package uo.ri.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.workOrder.WorkOrderService;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

import java.util.Optional;

public class ViewWorkOrderDetailAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");

		WorkOrderService as = ServiceFactory.getWorkOrderService();
		Optional<WorkOrderDto> oWo = as.findWorkOrderById(woId);

		if ( oWo.isPresent() ) {
			Printer.printWorkOrderDetail( oWo.get() );
		} else {
			Console.println("There is no work order with that id");
		}

	}
}
