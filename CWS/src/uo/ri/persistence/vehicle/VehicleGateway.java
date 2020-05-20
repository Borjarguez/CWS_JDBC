package uo.ri.persistence.vehicle;

import uo.ri.business.dto.VehicleDto;

import java.sql.Connection;
import java.util.Optional;

public interface VehicleGateway {
	/**
	 * Method which sets the connection
	 * 
	 * @param con
	 */
	void setConnection(Connection con);

	/**
	 * Method which finds a vehicle by its plate
	 * 
	 * @param plate
	 * @return
	 */
	Optional<VehicleDto> findByPlate(String plate);

	/**
	 * Method which finds the vehicle by its id
	 * 
	 * @param id
	 * @return
	 */
	VehicleDto findVehicleById(Long id);
}
