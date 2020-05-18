package uo.ri.business.TransactionScripts.courseAttendance;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.course.CourseGateway;
import uo.ri.persistence.courseAttendance.CourseAttendanceGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindAttendanceByCourseId {
	private Long course_id;

	public FindAttendanceByCourseId(Long course_id) {
		this.course_id = course_id;
	}

	public List<EnrollmentDto> execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);

			CourseAttendanceGateway cg = PersistenceFactory.getCourseAttendanceGateway();
			CourseGateway cgg = PersistenceFactory.getCourseGateway();

			cg.setConnection(c);
			cgg.setConnection(c);

			if (cgg.findCourseByID(course_id) == null) {
				c.rollback();
				throw new BusinessException("Course doesn't exist");
			}

			return cg.findAttendanceByCourseId(course_id);
		} catch (SQLException e) {
			throw new RuntimeException("Connection error");
		}
	}

}
