package uo.ri.persistence;

import uo.ri.business.dto.VehicleTypeDto;

import java.sql.Connection;
import java.util.List;

public interface VehicleTypeGateway {

	void setConnection(Connection con);

	List<VehicleTypeDto> findAll();
	
	VehicleTypeDto findByID(Long idVehicleType);
}
