package uo.ri.business.TransactionScripts.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.course.CourseGateway;

public class FindCourseByID {
    private Long course_id;

    public FindCourseByID(Long course_id){
        this.course_id = course_id;
    }

    public Optional<CourseDto> execute() throws BusinessException {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            CourseGateway mg = PersistenceFactory.getCourseGateway();
            mg.setConnection(c);
            Optional<CourseDto> course = Optional.ofNullable(mg.findCourseByID(course_id));

            if(course == null){
                c.rollback();
                throw new BusinessException("Course doesn't exist");
            }

            return course;
        } catch (SQLException e) {
            throw new RuntimeException("Connection error");
        }
    }
}
