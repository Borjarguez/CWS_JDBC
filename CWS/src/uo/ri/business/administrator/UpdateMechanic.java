package uo.ri.business.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.dto.MechanicDto;

public class UpdateMechanic {
	private static String SQL = "update TMechanics " + "set name = ?, surname = ? " + "where id = ?";
	private MechanicDto m;
	
	
	public UpdateMechanic(MechanicDto m) {
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
			pst.setString(1, m.name);
			pst.setString(2, m.surname);
			pst.setLong(3, m.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

	}
}
