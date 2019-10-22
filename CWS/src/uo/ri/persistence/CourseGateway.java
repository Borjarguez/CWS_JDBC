package uo.ri.persistence;

import uo.ri.business.dto.CourseDto;

import java.sql.Connection;
import java.util.List;

public interface CourseGateway {

    void setConnection(Connection con);

    List<Long> findCourseByMechanicIdVehicleTypeId(Long mechanic_id, Long vehicleType_id);

    CourseDto findCourseByID(Long course_id);

    int getPercentageByVehicleTypeID(Long course_id, Long vehicleType_id);

    int getAttendanceByVehicleTypeID(Long course_id, Long mechanic_id);
}
