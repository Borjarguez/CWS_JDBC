package uo.ri.business.TransactionScripts.courseReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseAttendanceGateway;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.VehicleTypeGateway;

public class FindTrainingByMechanicID {
    private Long mechanic_id;

    public FindTrainingByMechanicID(Long mechanic_id) {
        this.mechanic_id = mechanic_id;
    }

    public List<TrainingForMechanicRow> execute() {
        List<Long> vehicleTypes_ids = getVehicleTypeIDs();
        List<TrainingForMechanicRow> list = new ArrayList<>();
        TrainingForMechanicRow nw;
        List<CourseDto> courses;

        int attendance, dedication, hours;

        for (Long vehicleType_id : vehicleTypes_ids) {
            nw = new TrainingForMechanicRow();
            courses = getCourses(vehicleType_id);
            if (courses != null) {
                nw.vehicleTypeName = getVehicleTypeName(vehicleType_id);
                for (CourseDto course : courses) {
                    attendance = getAttendanceForCourse(course.id);
                    hours = course.hours;
                    dedication = getDedicationForVehicleType(course.id, vehicleType_id);
                    nw.enrolledHours += (hours*dedication)/100;
                    nw.attendedHours += (((attendance * hours) / 100)*dedication)/100;
                }
                list.add(nw);
            }
        }
        return list;
    }

    private List<CourseDto> getCourses(Long vehicleType_id) {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            CourseGateway vg = PersistenceFactory.getCourseGateway();
            vg.setConnection(c);
            return vg.findCoursesForVehicleType(vehicleType_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getVehicleTypeName(Long vehicleType_id) {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            VehicleTypeGateway vg = PersistenceFactory.getVehicleTypeGateway();
            vg.setConnection(c);
            return vg.findByID(vehicleType_id).name;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getDedicationForVehicleType(Long course_id, Long vehicleType_id) {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            VehicleTypeGateway vg = PersistenceFactory.getVehicleTypeGateway();
            vg.setConnection(c);
            return vg.findDedicationForVehicleType(course_id, vehicleType_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Long> getVehicleTypeIDs() {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            VehicleTypeGateway vg = PersistenceFactory.getVehicleTypeGateway();
            vg.setConnection(c);
            return vg.findVehicleTypesByMechanicID(mechanic_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getAttendanceForCourse(Long course_id) {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            CourseAttendanceGateway mg = PersistenceFactory.getCourseAttendanceGateway();
            mg.setConnection(c);
            return mg.findAttendedHoursByMechanicIDCourseID(course_id, mechanic_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
