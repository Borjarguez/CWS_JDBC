package uo.ri.business.ServiceLayer.vehicle;

import uo.ri.business.dto.VehicleDto;
import uo.ri.common.BusinessException;

import java.util.Optional;

/**
 * This service is intended to be used by the Foreman
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface VehicleCrudService {

	/**
	 * @param plate number
	 * @return an Optional with the vehicle dto specified be the plate number
	 * 
	 * @throws BusinessException, DOES NOT 
	 */
	Optional<VehicleDto> findVehicleByPlate(String plate) throws BusinessException;
	
	/**
	 * 
	 * @param id, the vehicle id
	 * @return the vehicle dto
	 * @throws BusinessException
	 */
	VehicleDto findVehicleById(Long id) throws BusinessException;
	
}
