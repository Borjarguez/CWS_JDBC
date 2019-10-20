package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.VehicleTypeGateway;

public class VehicleTypeGatewayImpl implements VehicleTypeGateway {

	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public List<VehicleTypeDto> findAll() {
		List<VehicleTypeDto> list = new ArrayList<VehicleTypeDto>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLETYPES");

		try (PreparedStatement pst = c.prepareStatement(SQL); ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				VehicleTypeDto v = new VehicleTypeDto();
				v.id = rs.getLong("id");
				v.name = rs.getString("name");
				v.pricePerHour = rs.getDouble("priceperhour");
				v.minTrainigHours = rs.getInt("mintraininghours");
				
				list.add(v);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
