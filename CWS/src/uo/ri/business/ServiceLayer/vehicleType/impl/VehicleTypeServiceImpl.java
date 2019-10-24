package uo.ri.business.ServiceLayer.vehicleType.impl;

import uo.ri.business.ServiceLayer.vehicleType.VehicleTypeService;
import uo.ri.business.TransactionScripts.vehicleType.ListVehicleTypes;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;

import java.util.List;

public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Override
	public List<VehicleTypeDto> findAll() throws BusinessException {
		ListVehicleTypes lvt = new ListVehicleTypes();
		return lvt.execute();
	}
	
	
}
