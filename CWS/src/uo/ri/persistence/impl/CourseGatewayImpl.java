package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.CourseGateway;

public class CourseGatewayImpl implements CourseGateway {
	private Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public List<Long> findCourseByMechanicIdVehicleTypeId(Long vehicleType_id, Long mechanic_id) {
		List<Long> courses_ids = new ArrayList<Long>();
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSES_BY_MECHANICID_VEHICLETYPEID");
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, vehicleType_id);
			pst.setLong(2, mechanic_id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				courses_ids.add(rs.getLong("course_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses_ids;
	}

	@Override
	public CourseDto findCourseByID(Long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		CourseDto c = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_ID");

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			if (!rs.next())
				return c;
			else {
				c = new CourseDto();
				c.id = rs.getLong("id");
				c.name = rs.getString("name");
				c.description = rs.getString("description");
				c.hours = rs.getInt("hours");
				c.startDate = rs.getDate("startDate");
				c.endDate = rs.getDate("endDate");
			}

			return c;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getPercentageByVehicleTypeID(Long course_id, Long vehicleType_id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_PERCENTAGE");

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, course_id);
			pst.setLong(2, vehicleType_id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getAttendanceByVehicleTypeID(Long course_id, Long mechanic_id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_ATTENDANCE");

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, course_id);
			pst.setLong(2, mechanic_id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
