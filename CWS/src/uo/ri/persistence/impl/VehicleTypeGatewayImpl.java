package uo.ri.persistence.impl;

import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.VehicleTypeGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		System.out.println(SQL);
		System.out.println(c);
		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			try( ResultSet rs = pst.executeQuery()){
				while (rs.next()) {
					VehicleTypeDto v = new VehicleTypeDto();
					v.id = rs.getLong("id");
					v.name = rs.getString("name");
					v.pricePerHour = rs.getDouble("priceperhour");
					v.minTrainigHours = rs.getInt("mintraininghours");
					
					list.add(v);
				}	
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public VehicleTypeDto findByID(Long idVehicleType) {
		VehicleTypeDto v = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLETYPE_ID");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setLong(1, idVehicleType);
			try(ResultSet rs = pst.executeQuery()){
				if (!rs.next())
					return v;
				else {
					v = new VehicleTypeDto();
					v.id = rs.getLong("id");
					v.name = rs.getString("name");
					v.pricePerHour = rs.getDouble("priceperhour");
					v.minTrainigHours = rs.getInt("mintraininghours");
				}
			}
			
			return v;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
