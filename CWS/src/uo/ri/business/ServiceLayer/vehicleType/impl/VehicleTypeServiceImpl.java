package uo.ri.business.ServiceLayer.vehicleType.impl;

import java.util.List;

import uo.ri.business.ServiceLayer.vehicleType.VehicleTypeService;
import uo.ri.business.TransactionScripts.vehicleType.ListVehicleTypes;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;

public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Override
	public List<VehicleTypeDto> findAll() throws BusinessException {
		ListVehicleTypes lvt = new ListVehicleTypes();
		return lvt.execute();
	}
	
	
}
