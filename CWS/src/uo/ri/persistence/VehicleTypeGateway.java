package uo.ri.persistence;

import uo.ri.business.dto.VehicleTypeDto;

import java.sql.Connection;
import java.util.List;

public interface VehicleTypeGateway {

	/**
	 * Method which sets the connection
	 * @param con
	 */
	void setConnection(Connection con);

	/**
	 * Method which finds all the vehicle types
	 * @return
	 */
	List<VehicleTypeDto> findAll();
	
	/**
	 * Method which finds the vehicle type by its id
	 * @param idVehicleType
	 * @return
	 */
	VehicleTypeDto findByID(Long idVehicleType);

	/**
	 * Method which finds a vehicle type 
	 * @param mechanic_id
	 * @return
	 */
	List<Long> findVehicleTypesByMechanicID(Long mechanic_id);

	/**
	 * Method which finds the dedication
	 * @param course_id
	 * @param vehicleType_Id
	 * @return
	 */
	int findDedicationForVehicleType(Long course_id, Long vehicleType_Id);
}
