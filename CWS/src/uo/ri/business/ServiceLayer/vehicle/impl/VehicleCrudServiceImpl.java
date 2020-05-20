package uo.ri.business.ServiceLayer.vehicle.impl;

import java.util.Optional;

import uo.ri.business.ServiceLayer.vehicle.VehicleCrudService;
import uo.ri.business.TransactionScripts.vehicle.FindVehicleByID;
import uo.ri.business.TransactionScripts.vehicle.FindVehicleByPlate;
import uo.ri.business.dto.VehicleDto;
import uo.ri.common.BusinessException;

public class VehicleCrudServiceImpl implements VehicleCrudService{

	@Override
	public Optional<VehicleDto> findVehicleByPlate(String plate) throws BusinessException {
		FindVehicleByPlate flp = new FindVehicleByPlate(plate);
		return flp.execute();
	}

	@Override
	public VehicleDto findVehicleById(Long id) throws BusinessException {
		FindVehicleByID fvd = new FindVehicleByID(id);
		return fvd.execute();
	}

}
