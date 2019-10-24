package uo.ri.persistence.impl;

import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.CourseGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseGatewayImpl implements CourseGateway {
	private Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public List<Long> findCourseByMechanicIdVehicleTypeId(Long vehicleType_id, Long mechanic_id) {
		List<Long> courses_ids = new ArrayList<>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSES_BY_MECHANICID_VEHICLETYPEID");

		try {
			PreparedStatement pst = con.prepareStatement(SQL);
			pst.setLong(1, vehicleType_id);
			pst.setLong(2, mechanic_id);

			ResultSet rs = pst.executeQuery();
			
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
		PreparedStatement pst;
		ResultSet rs;
		CourseDto dto;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_ID");

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			
			if (!rs.next())
				return null;
			else {
				dto = getCourseDto(rs);
			}
			return dto;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getPercentageByVehicleTypeID(Long course_id, Long vehicleType_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_PERCENTAGE");
		return tryIt(course_id, vehicleType_id, SQL);
	}

	@Override
	public int getAttendanceByVehicleTypeID(Long course_id, Long mechanic_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_ATTENDANCE");
		return tryIt(course_id, mechanic_id, SQL);
	}

	@Override
	public List<CourseDto> findAll() {
		List<CourseDto> courses = new ArrayList<>();
		PreparedStatement pst;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_COURSES");
		ResultSet rs;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();
			CourseDto dto;

			while(rs.next()) {
				dto = getCourseDto(rs);
				courses.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	private CourseDto getCourseDto(ResultSet rs) throws SQLException {
		CourseDto dto;
		dto = new CourseDto();
		dto.id = rs.getLong("id");
		dto.name = rs.getString("name");
		dto.description = rs.getString("description");
		dto.hours = rs.getInt("hours");
		dto.startDate = rs.getDate("startDate");
		dto.endDate = rs.getDate("endDate");
		return dto;
	}

	private int tryIt(Long course_id, Long vehicleType_id, String SQL) {
		PreparedStatement pst;
		ResultSet rs;
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
}
