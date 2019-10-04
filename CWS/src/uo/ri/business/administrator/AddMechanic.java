package uo.ri.business.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.dto.MechanicDto;

public class AddMechanic {
	private static String SQL = "insert into TMechanics(dni, name, surname) values (?, ?, ?)";
	private MechanicDto m; 
	
	public AddMechanic(MechanicDto m) {
		this.m = m;
	}

	public void execute() {
		
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, m.dni);
			pst.setString(2, m.name);
			pst.setString(3, m.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}
}
