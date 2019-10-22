package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.CourseAttendanceGateway;

public class CourseAttendanceGatewayImpl implements CourseAttendanceGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long course_id) {
		List<EnrollmentDto> list = new ArrayList<EnrollmentDto>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_COURSE_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			rs = pst.executeQuery();
			
			EnrollmentDto m = null;
			
			while(rs.next()) {
				m = new EnrollmentDto();
				m.id = rs.getLong("id");
				m.mechanicId = rs.getString("mechanic_id");
				m.courseId = rs.getString("course_id");
				m.attendance = rs.getInt("attendance");
				m.passed = rs.getBoolean("passed");
				
				list.add(m);
			}			
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}

	@Override
	public EnrollmentDto findAttendanceByCourseIDMechanicID(Long course_id, Long mechanic_id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_COURSE_ID_MEHCANIC_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, course_id);
			pst.setLong(2, mechanic_id);
			rs = pst.executeQuery();
			
			EnrollmentDto m = null;
			
			while(rs.next()) {
				m = new EnrollmentDto();
				m.id = rs.getLong("id");
				m.mechanicId = rs.getString("mechanic_id");
				m.courseId = rs.getString("course_id");
				m.attendance = rs.getInt("attendance");
				m.passed = rs.getBoolean("passed");
			}		
			
			return m;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
