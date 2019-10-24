package uo.ri.business.ServiceLayer.vehicleType;

import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;

import java.util.List;

public interface VehicleTypeService {
	List<VehicleTypeDto> findAll() throws BusinessException;
}
