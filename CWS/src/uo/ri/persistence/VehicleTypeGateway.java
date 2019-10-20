package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;

public interface VehicleTypeGateway {

	void setConnection(Connection con);

	List<VehicleTypeDto> findAll();
}
