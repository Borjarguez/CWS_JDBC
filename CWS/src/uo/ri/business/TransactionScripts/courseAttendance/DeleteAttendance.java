package uo.ri.business.TransactionScripts.courseAttendance;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseAttendanceGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteAttendance {
    private Long attendance_id;

    public DeleteAttendance(Long attendance_id) {
        this.attendance_id = attendance_id;
    }

    public void execute() throws BusinessException {
        try(Connection c = Jdbc.getConnection()){
            c.setAutoCommit(false);
            CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
            cag.setConnection(c);

            /////////////// Security checks ///////////////////////////////////
            if(cag.findByID(attendance_id) == null){
                c.rollback();
                throw new BusinessException("Attendance doesn't exist");
            }
            ///////////////////////////////////////////////////////////////////
            cag.delete(attendance_id);
            c.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
