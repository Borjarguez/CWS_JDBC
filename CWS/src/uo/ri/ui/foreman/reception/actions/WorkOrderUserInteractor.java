package uo.ri.ui.foreman.reception.actions;

import alb.util.console.Console;
import uo.ri.business.ServiceLayer.vehicle.VehicleCrudService;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

import java.util.Optional;

public class WorkOrderUserInteractor {

	public WorkOrderDto askForWorkOrder(VehicleDto v) {
		WorkOrderDto wo = new WorkOrderDto();
		wo.description = Console.readString("Work description");
		wo.vehicleId = v.id;
		return wo;
	}

	public VehicleDto askForVehicle() throws BusinessException {
		String plate = Console.readString("Plate number");

		VehicleCrudService vs = ServiceFactory.getVehicleCrudService();
		Optional<VehicleDto> ov = vs.findVehicleByPlate(plate);
		assertPresent(ov);

		return ov.get();
	}

	private void assertPresent(Optional<?> o) throws BusinessException {
		if ( o.isPresent() ) return;
		throw new BusinessException("There is no such entity");
	}

}
