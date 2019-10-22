package uo.ri.persistence.impl;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.CertificateGateway;

import java.sql.*;
import java.util.Calendar;

public class CertificateGatewayImpl implements CertificateGateway{
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public void generateCertificates(Long idMechanic, Long idVehicleType) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_CERTIFICATE");
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			pst.setLong(2, idMechanic);
			pst.setLong(3, idVehicleType);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public boolean doesCertificateExist(Long idMechanic, Long idVehicle) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_EXIST_CERTIFICATE");
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			pst.setLong(2, idVehicle);
			rs = pst.executeQuery();
			
			if(!rs.next()) {
				return false;
			}
			
			return rs.getInt(1) > 0 ? true : false;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

}
