package uo.ri.ui.foreman.reception.actions;

import java.util.List;
import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.vehicle.VehicleCrudService;
import uo.ri.business.ServiceLayer.workOrder.WorkOrderService;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		WorkOrderService ws = ServiceFactory.getWorkOrderService();
		VehicleCrudService ve = ServiceFactory.getVehicleCrudService();

		Long woId = Console.readLong("Work order id");

		Optional<WorkOrderDto> wo = ws.findWorkOrderById(woId);

		if (!wo.isPresent())
			throw new BusinessException("That work order doesn't exist");
		
		if (!wo.get().status.equals("OPEN"))
			throw new BusinessException("The work order is not OPENNED");

		VehicleDto v = ve.findVehicleById(wo.get().vehicleId);
		List<CertificateDto> certificates = ws.findCertificatesByVehicleTypeId(v.vehicleTypeId);
		certificates.forEach((c) -> Printer.printCertifiedMechanic(c));

		Long mId = Console.readLong("Mechanic id");
		boolean correct = false;

		for (CertificateDto c : certificates)
			if (c.mechanic.id == mId)
				correct = true;

		while (!correct) {
			mId = Console.readLong("Mechanic id");
			for (CertificateDto ce : certificates)
				if (ce.mechanic.id == mId)
					correct = true;
		}

		WorkOrderService as = ServiceFactory.getWorkOrderService();
		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
}
