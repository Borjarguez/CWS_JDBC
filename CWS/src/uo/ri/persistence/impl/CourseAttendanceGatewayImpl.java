package uo.ri.persistence.impl;

import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseAttendanceGateway;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseAttendanceGatewayImpl implements CourseAttendanceGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public void add(EnrollmentDto e) {
		String SQL = Conf.getInstance().getProperty("SQL_ADD_ENROLLMENT");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setInt(1, e.attendance);
			pst.setBoolean(2, e.passed);
			pst.setLong(3, e.courseId);
			pst.setLong(4, e.mechanicId);

			pst.executeUpdate();

		} catch (SQLException s) {
			throw new RuntimeException(s);
		}
	}

	@Override
	public void delete(Long attendance_id) {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_ATTENDANCE");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setLong(1, attendance_id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public EnrollmentDto findByID(Long attendance_id) {
		EnrollmentDto e;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_ID");

		try (PreparedStatement pst = c.prepareStatement(SQL)) {
			pst.setLong(1, attendance_id);
			try (ResultSet rs = pst.executeQuery()) {

				if (!rs.next())
					return null;
				else {
					e = new EnrollmentDto();
					e.id = rs.getLong("id");
					e.mechanicId = rs.getLong("mechanic_id");
					e.courseId = rs.getLong("course_id");
					e.attendance = rs.getInt("attendance");
					e.passed = rs.getBoolean("passed");
				}
			}
			return e;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long course_id) {
		MechanicGateway mg = PersistenceFactory.getMechanicGateway();
		mg.setConnection(c);
		List<EnrollmentDto> list = new ArrayList<>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_COURSE_ID");
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			rs = pst.executeQuery();

			while(rs.next()) {
				EnrollmentDto m = getEnrollmentDto(rs);
				m.mechanic = mg.findByID(m.mechanicId);
				list.add(m);
			}			
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}

	@Override
	public Long findLastAttendance() {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_LAST_ATTENDANCE");
		PreparedStatement pst;
		ResultSet rs;

		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public EnrollmentDto findAttendanceByCourseIDMechanicID(Long course_id, Long mechanic_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_COURSE_ID_MEHCANIC_ID");
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			pst.setLong(2, mechanic_id);
			rs = pst.executeQuery();
			
			EnrollmentDto m = null;
			
			while(rs.next()) {
				m = getEnrollmentDto(rs);
			}		
			
			return m;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private EnrollmentDto getEnrollmentDto(ResultSet rs) throws SQLException {
		EnrollmentDto e = new EnrollmentDto();
		e.id = rs.getLong("id");
		e.mechanicId = rs.getLong("mechanic_id");
		e.courseId = rs.getLong("course_id");
		e.attendance = rs.getInt("attendance");
		e.passed = rs.getBoolean("passed");
		return e;
	}

}
