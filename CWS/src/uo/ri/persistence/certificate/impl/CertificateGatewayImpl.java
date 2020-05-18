package uo.ri.persistence.certificate.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.certificate.CertificateGateway;

public class CertificateGatewayImpl implements CertificateGateway {
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

			if (!rs.next()) {
				return false;
			}

			return rs.getInt(1) > 0 ? true : false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<CertificateDto> findAllOrdered() {
		List<CertificateDto> certificates = new ArrayList<CertificateDto>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_CERTIFICATES_FIND_ORDERED_VEHICLETYPE");
		CertificateDto certificate = null;
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			while (rs.next()) {
				certificate = new CertificateDto();
				certificate.id = rs.getLong("id");
				certificate.obtainedAt = rs.getDate("date");
				certificate.mechanic = new MechanicDto();
				certificate.mechanic.id = rs.getLong("mechanic_id");
				certificate.vehicleType = new VehicleTypeDto();
				certificate.vehicleType.id = rs.getLong("vehicletype_id");
				certificates.add(certificate);
			}

			return certificates;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
