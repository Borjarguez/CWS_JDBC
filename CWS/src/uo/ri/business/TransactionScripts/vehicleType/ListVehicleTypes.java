package uo.ri.business.TransactionScripts.vehicleType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.VehicleTypeGateway;

public class ListVehicleTypes {
	
	public List<VehicleTypeDto> execute() {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			 VehicleTypeGateway vg = PersistenceFactory.getVehicleTypeGateway();
			return vg.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
	
}
