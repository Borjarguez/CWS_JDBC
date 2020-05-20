package uo.ri.persistence.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.vehicle.VehicleGateway;

public class VehicleGatewayImpl implements VehicleGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public Optional<VehicleDto> findByPlate(String plate) {
		PreparedStatement pst;
		VehicleDto v;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE");

		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, plate);
			try (ResultSet rs = pst.executeQuery()) {
				if (!rs.next())
					return Optional.empty();
				else {
					v = new VehicleDto();
					v.id = rs.getLong("id");
					v.make = rs.getString("make");
					v.model = rs.getString("model");
					v.plate = rs.getString("platenumber");
					v.clientId = rs.getLong("client_id");
					v.vehicleTypeId = rs.getLong("vehicletype_id");
				}
			}
			return Optional.of(v);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public VehicleDto findVehicleById(Long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		VehicleDto v = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_ID");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				v = new VehicleDto();
				v.id = rs.getLong("id");
				v.make = rs.getString("make");
				v.model = rs.getString("model");
				v.plate = rs.getString("platenumber");
				v.clientId = rs.getLong("client_id");
				v.vehicleTypeId = rs.getLong("vehicletype_id");
			}
			return v;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
