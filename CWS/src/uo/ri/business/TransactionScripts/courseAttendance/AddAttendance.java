package uo.ri.business.TransactionScripts.courseAttendance;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseAttendanceGateway;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class AddAttendance {
    private EnrollmentDto dto;

    public AddAttendance(EnrollmentDto dto) {
        this.dto = dto;
    }

    public EnrollmentDto execute() throws BusinessException {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);

            CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
            MechanicGateway mg = PersistenceFactory.getMechanicGateway();
            CourseGateway cg = PersistenceFactory.getCourseGateway();

            cag.setConnection(c);
            mg.setConnection(c);
            cg.setConnection(c);

            /////////////// Security checks ///////////////////////////////////
            // Percentage must be between 0 and 100
            if (dto.attendance < 0 || dto.attendance > 100) {
                c.rollback();
                throw new BusinessException("Attendance must be between 0 and 100");
            }
            // Mechanic exists
            if (mg.findByID(dto.mechanicId) == null) {
                c.rollback();
                throw new BusinessException("Mechanic doesn't exist");
            }
            // Course exists
            if (cg.findCourseByID(dto.courseId) == null) {
                c.rollback();
                throw new BusinessException("Course doesn't exist");
            }
            // 85% overrated
            if (dto.attendance < 85 && dto.passed) {
                c.rollback();
                throw new BusinessException("Attendance must be over 85%");
            }
            // Repeated attendance
            if (cag.findAttendanceByCourseIDMechanicID(dto.courseId, dto.mechanicId) != null) {
                c.rollback();
                throw new BusinessException("Attendance already exists");
            }
            ///////////////////////////////////////////////////////////////////

            cag.add(dto);
            dto.id = cag.findLastAttendance();

            c.commit();
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
