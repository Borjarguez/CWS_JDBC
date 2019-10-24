package uo.ri.business.ServiceLayer.vehicle.impl;

import uo.ri.business.ServiceLayer.vehicle.VehicleCrudService;
import uo.ri.business.TransactionScripts.vehicle.FindVehicleByPlate;
import uo.ri.business.dto.VehicleDto;
import uo.ri.common.BusinessException;

import java.util.Optional;

public class VehicleCrudServiceImpl implements VehicleCrudService{

	@Override
	public Optional<VehicleDto> findVehicleByPlate(String plate) throws BusinessException {
		FindVehicleByPlate flp = new FindVehicleByPlate(plate);
		return flp.execute();
	}

}
