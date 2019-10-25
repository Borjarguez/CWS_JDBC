package uo.ri.business.TransactionScripts.course;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseAttendanceGateway;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.DedicationGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCourse {
    private Long course_id;

    public DeleteCourse(Long id) {
        this.course_id = id;
    }

    public void execute() throws BusinessException {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            CourseGateway mg = PersistenceFactory.getCourseGateway();
            CourseAttendanceGateway cgg = PersistenceFactory.getCourseAttendanceGateway();
            DedicationGateway dg = PersistenceFactory.getDedicationsGateway();

            mg.setConnection(c);
            cgg.setConnection(c);
            dg.setConnection(c);

            //////////////////// Security checks //////////////////////////////////////////////////
            // Check the course exists
            if (mg.findCourseByID(course_id) == null) {
                c.rollback();
                throw new BusinessException("Course doesn't exist");
            }

            // Check the course doesn't have mechanics assigned
            if(cgg.checkCourseIsAssigned(course_id)){
                c.rollback();
                throw new BusinessException("Course can't be deleted: It has mechanics assigned");
            }
            ///////////////////////////////////////////////////////////////////////////////////////
            // TODO borrar el curso de Dedications
            mg.delete(course_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
