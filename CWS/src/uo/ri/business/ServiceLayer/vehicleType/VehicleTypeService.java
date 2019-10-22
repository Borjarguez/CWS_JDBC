package uo.ri.business.ServiceLayer.vehicleType;

import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;

public interface VehicleTypeService {
	List<VehicleTypeDto> findAll() throws BusinessException;
}
