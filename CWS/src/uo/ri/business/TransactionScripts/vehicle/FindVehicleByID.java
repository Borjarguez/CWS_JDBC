package uo.ri.business.TransactionScripts.vehicle;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.vehicle.VehicleGateway;

public class FindVehicleByID {
	private Long id;

	public FindVehicleByID(Long id) {
		this.id = id;
	}

	public VehicleDto execute() {
		try (Connection c = Jdbc.getConnection()) {
			VehicleGateway mg = PersistenceFactory.getVehicleGateway();
			mg.setConnection(c);
			return mg.findVehicleById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}
}
