package uo.ri.business.TransactionScripts.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class UpdateCourse {
    private CourseDto dto;

    public UpdateCourse(CourseDto dto){
        this.dto = dto;
    }

    public void execute() throws BusinessException {
        try(Connection c = Jdbc.getConnection()){
            c.setAutoCommit(false);
            CourseGateway cg = PersistenceFactory.getCourseGateway();
            cg.setConnection(c);

            // Security checks
            if(cg.findCourseByID(dto.id) == null){
                c.rollback();
                throw new BusinessException("Course doesn't exist");
            }

            if(cg.hasBeenImparted(dto.id)){
                c.rollback();
                throw new BusinessException("Course has been imparted at any time");
            }
            ////////////////////////////////////////////////////////

            cg.update(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
