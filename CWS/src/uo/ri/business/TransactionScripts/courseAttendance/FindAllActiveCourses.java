package uo.ri.business.TransactionScripts.courseAttendance;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindAllActiveCourses {

    public List<CourseDto> execute() {
        try(Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            CourseGateway cg = PersistenceFactory.getCourseGateway();
            cg.setConnection(c);
            return cg.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
