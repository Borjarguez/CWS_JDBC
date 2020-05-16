package uo.ri.persistence.courseAttendance;

import uo.ri.business.dto.EnrollmentDto;

import java.sql.Connection;
import java.util.List;

public interface CourseAttendanceGateway {

	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection c);

    /**
     * Method which adds the element to the database
     * @param e
     */
    void add(EnrollmentDto e);

    /**
     * Method which deletes
     * @param attendance_id
     */
    void delete(Long attendance_id);

    /**
     * Method which finds
     * @param attendance_id
     * @return
     */
    EnrollmentDto findByID(Long attendance_id);

    /**
     * Method which finds
     * @param course_id
     * @param mechanic_id
     * @return
     */
    EnrollmentDto findAttendanceByCourseIDMechanicID(Long course_id, Long mechanic_id);

    /**
     * Method which finds
     * @param course_id
     * @return
     */
    List<EnrollmentDto> findAttendanceByCourseId(Long course_id);

    /**
     * Method which finds the last attendance
     * @return
     */
    Long findLastAttendance();

    /**
     * Method which finds
     * @param course_id
     * @return
     */
    int findAttendedHoursCourseID(long course_id);

    /**
     * Method which finds
     * @param course_id
     * @param mechanic_id
     * @return
     */
    int findAttendedHoursByMechanicIDCourseID(Long course_id, Long mechanic_id);

    /**
     * Method which finds
     * @param mechanic_id
     * @param vehicleType_id
     * @return
     */
    int findAttendanceForMechanic(Long mechanic_id, Long vehicleType_id);

    /**
     * Method which checks
     * @param course_id
     * @return
     */
    boolean checkCourseIsAssigned(Long course_id);
}
