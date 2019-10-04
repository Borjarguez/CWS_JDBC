package uo.ri.business.impl.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.dto.MechanicDto;

public class ListMechanic {
	private static String SQL = "select id, dni, name, surname from TMechanics";
	
	public List<MechanicDto> execute() {
		List<MechanicDto> list = new ArrayList<MechanicDto>();
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				MechanicDto m = new MechanicDto();
				m.id = rs.getLong("id");
				m.name = rs.getString("name");
				m.dni = rs.getString("dni");
				m.surname = rs.getString("surname");
				list.add(m);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		return list;
	}
}
