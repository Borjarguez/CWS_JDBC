package uo.ri.persistence;

import uo.ri.business.dto.VehicleDto;

import java.sql.Connection;
import java.util.Optional;

public interface VehicleGateway {
	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection con);

    /**
     * Method which finds a vehicle by its plate
     * @param plate
     * @return
     */
    Optional<VehicleDto> findByPlate(String plate);
}
