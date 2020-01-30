package uo.ri.business.TransactionScripts.course;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindAllCourses {

    public List<CourseDto> execute() throws BusinessException {
        return getCourseDtos();
    }

    public static List<CourseDto> getCourseDtos() throws BusinessException {
        try(Connection c = Jdbc.getConnection()){
            c.setAutoCommit(false);
            CourseGateway mg = PersistenceFactory.getCourseGateway();
            mg.setConnection(c);
            List<CourseDto> list = mg.findAll();

            if(list == null){
                c.rollback();
                throw new BusinessException("Problems finding courses in the Databes");
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}