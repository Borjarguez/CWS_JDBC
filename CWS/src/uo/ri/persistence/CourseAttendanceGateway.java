package uo.ri.persistence;

import uo.ri.business.dto.EnrollmentDto;

import java.sql.Connection;
import java.util.List;

public interface CourseAttendanceGateway {

    void setConnection(Connection c);

    void add(EnrollmentDto e);

    void delete(Long attendance_id);

    EnrollmentDto findByID(Long attendance_id);

    EnrollmentDto findAttendanceByCourseIDMechanicID(Long course_id, Long mechanic_id);

    List<EnrollmentDto> findAttendanceByCourseId(Long course_id);

    Long findLastAttendance();

}
