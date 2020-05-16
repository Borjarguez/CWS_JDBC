package uo.ri.persistence.dedication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uo.ri.conf.Conf;
import uo.ri.persistence.dedication.DedicationGateway;

public class DedicationGatewayImpl implements DedicationGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public void add(Long typeID, Integer percentage, Long courseID) {
		String SQL = Conf.getInstance().getProperty("SQL_ADD_DEDICATION");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setLong(1, typeID);
			pst.setLong(2, courseID);
			pst.setInt(3, percentage);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByCourseID(Long course_id) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_DEDICATION_BY_COURSE_ID");
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
