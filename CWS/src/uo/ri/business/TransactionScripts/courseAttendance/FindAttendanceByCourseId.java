package uo.ri.business.TransactionScripts.courseAttendance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseAttendanceGateway;

public class FindAttendanceByCourseId {
	private Long course_id;

	public FindAttendanceByCourseId(Long course_id) {
		this.course_id = course_id;
	}
	
	public List<EnrollmentDto> execute() {
		try(Connection c = Jdbc.getConnection()){
			c.setAutoCommit(false);
			CourseAttendanceGateway cg = PersistenceFactory.getCourseAttendanceGateway();
			cg.setConnection(c);
			return cg.findAttendanceByCourseId(course_id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
