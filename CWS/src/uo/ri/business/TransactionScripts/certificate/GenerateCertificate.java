package uo.ri.business.TransactionScripts.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CertificateGateway;

public class GenerateCertificate {
		
	public int execute() {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			CertificateGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);

			// Obtener los tipos de vehiculo de la base
			List<VehicleTypeDto> vehiclesTypes = cg.findVehiclesTypes();
			
			// Para cada tipo de vehiculo hay q sacar los mecanicos
			for(VehicleTypeDto v: vehiclesTypes) {
				
			}
			
			
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}

}
