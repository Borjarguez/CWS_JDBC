package uo.ri.persistence.course;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.CourseDto;

public interface CourseGateway {

	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection con);

    /**
     * Method which adds the element to the database
     * @param e
     */
    void add(CourseDto dto);

    /**
     * Method which deletes
     * @param course_id
     */
    void delete(Long course_id);

    /**
     * Method which updates
     * @param dto
     */
    void update(CourseDto dto);

    /**
     * Method which finds a course by the mechanic
     * @param mechanic_id
     * @param vehicleType_id
     * @return
     */
    List<Long> findCourseByMechanicIdVehicleTypeId(Long mechanic_id, Long vehicleType_id);

    /**
     * Method which finds a course by its id
     * @param course_id
     * @return
     */
    CourseDto findCourseByID(Long course_id);

    /**
     * 
     * @param course_id
     * @param vehicleType_id
     * @return
     */
    int getPercentageByVehicleTypeID(Long course_id, Long vehicleType_id);

    /**
     * 
     * @param course_id
     * @param mechanic_id
     * @return
     */
    int getAttendanceByVehicleTypeID(Long course_id, Long mechanic_id);

    /**
     * Method which finds all the courses
     * @return
     */
    List<CourseDto> findAll();

    /**
     * Method which finds the course hours
     * @param course_id
     * @return
     */
    int findCourseHours(long course_id);

    /**
     * Method which finds
     * @param mechanic_id
     * @return
     */
    List<Long> findCoursesByMechanicID(Long mechanic_id);

    /**
     * Method which finds
     * @param mechanic_id
     * @return
     */
    int findDedicationByCourseID(long course_id);

    /**
     * Method which finds the courses related with the vehicle type 
     * @param vehicleType_id, the vehicle type
     * @return a list with the courses
     */
    List<CourseDto> findCoursesForVehicleType(Long vehicleType_id);
    
    /**
     * Method which finds
     * @param mechanic_id
     * @return
     */
    CourseDto findLastCourse();

    /**
     * Method which finds
     * @param mechanic_id
     * @return
     */
    boolean findCourseByCode(String code);

    /**
     * Method which finds
     * @param mechanic_id
     * @return
     */
    boolean hasBeenImparted(Long id);
}
