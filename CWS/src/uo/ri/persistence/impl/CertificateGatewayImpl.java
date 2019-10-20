package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.CertificateGateway;

public class CertificateGatewayImpl implements CertificateGateway{
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public int generateCertificates() {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_COURSE");
		
		try {
			pst = c.prepareStatement(SQL);
			
			// Cada tipo de vehiculo necesita un numero de horas de formacion
			// Se necesita registrar el % de horas que se dedica a cada tipo de vehiculo
			// El mecanico tiene el % de asistencia a clases (tabla 
			
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public List<VehicleTypeDto> findVehiclesTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
