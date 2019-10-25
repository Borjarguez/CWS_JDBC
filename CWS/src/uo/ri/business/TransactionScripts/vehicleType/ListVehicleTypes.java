package uo.ri.business.TransactionScripts.vehicleType;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.VehicleTypeGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListVehicleTypes {
	
	public List<VehicleTypeDto> execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			 VehicleTypeGateway vg = PersistenceFactory.getVehicleTypeGateway();
			 vg.setConnection(c);
			 List<VehicleTypeDto> list = vg.findAll();

			 if(list == null){
			 	c.rollback();
			 	throw new BusinessException("Error while finding vehicle types in the Database");
			 }

			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
	
}
