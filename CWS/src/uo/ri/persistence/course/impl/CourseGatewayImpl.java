package uo.ri.persistence.course.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.course.CourseGateway;

public class CourseGatewayImpl implements CourseGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public void add(CourseDto dto) {
		String SQL = Conf.getInstance().getProperty("SQL_ADD_COURSE");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setString(1, dto.code);
			pst.setString(2, dto.description);
			pst.setDate(3, new java.sql.Date(dto.endDate.getTime()));
			pst.setInt(4, dto.hours);
			pst.setString(5, dto.name);
			pst.setDate(6, new java.sql.Date(dto.startDate.getTime()));

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long course_id) {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_COURSE");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setLong(1, course_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(CourseDto dto) {
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_COURSE");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setString(1, dto.code);
			pst.setString(2, dto.description);
			pst.setDate(3, new java.sql.Date(dto.endDate.getTime()));
			pst.setInt(4, dto.hours);
			pst.setString(5, dto.name);
			pst.setDate(6, new java.sql.Date(dto.startDate.getTime()));
			pst.setLong(7, dto.id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Long> findCourseByMechanicIdVehicleTypeId(Long vehicleType_id, Long mechanic_id) {
		List<Long> courses_ids = new ArrayList<>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSES_BY_MECHANICID_VEHICLETYPEID");

		try {
			PreparedStatement pst = c.prepareStatement(SQL);
			pst.setLong(1, vehicleType_id);
			pst.setLong(2, mechanic_id);

			ResultSet rs = pst.executeQuery();

			while (rs.next())
				courses_ids.add(rs.getLong("course_id"));

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
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (!rs.next())
				return null;
			else
				dto = getCourseDto(rs);

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
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			CourseDto dto;

			while (rs.next()) {
				dto = getCourseDto(rs);
				courses.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public int findCourseHours(long course_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_HOURS");
		return findIDProperties(course_id, SQL);
	}

	private int findIDProperties(long course_id, String SQL) {
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			rs = pst.executeQuery();

			if (rs.next())
				return rs.getInt(1);

			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Long> findCoursesByMechanicID(Long mechanic_id) {
		List<Long> courses_ids = new ArrayList<>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSES_BY_MECHANIC_ID");
		try {
			PreparedStatement pst = c.prepareStatement(SQL);
			pst.setLong(1, mechanic_id);
			ResultSet rs = pst.executeQuery();

			while (rs.next())
				courses_ids.add(rs.getLong("course_id"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses_ids;
	}

	@Override
	public int findDedicationByCourseID(long course_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_DEDICATION");
		return findIDProperties(course_id, SQL);
	}

	@Override
	public List<CourseDto> findCoursesForVehicleType(Long vehicleType_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSES_FOR_VEHICLETYPE");
		List<CourseDto> list = new ArrayList<>();
		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setLong(1, vehicleType_id);
			try (ResultSet rs = pst.executeQuery()) {
				CourseDto co;
				while (rs.next()) {
					co = findCourseByID(rs.getLong("course_id"));
					list.add(co);
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CourseDto findLastCourse() {
		PreparedStatement pst;
		ResultSet rs;
		CourseDto dto;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_LAST_COURSE");

		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			dto = !rs.next() ? null : findCourseByID(rs.getLong(1));

			return dto;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existCourseByCode(String code) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_COURSE");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setString(1, code);
			ResultSet rs = pst.executeQuery();
			
			return !rs.next() ? false : true;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private CourseDto getCourseDto(ResultSet rs) throws SQLException {
		CourseDto dto;
		dto = new CourseDto();
		dto.id = rs.getLong("id");
		dto.code = rs.getString("code");
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
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			pst.setLong(2, vehicleType_id);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
