package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.EnrollmentDto;

public interface CourseAttendanceGateway {
	
	void setConnection(Connection c);

	EnrollmentDto findAttendanceByCourseIDMechanicID(Long course_id, Long mechanic_id);

	List<EnrollmentDto> findAttendanceByCourseId(Long course_id);
}
