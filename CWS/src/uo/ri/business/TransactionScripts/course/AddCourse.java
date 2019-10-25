package uo.ri.business.TransactionScripts.course;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.DedicationGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class AddCourse {
    private CourseDto dto;

    public AddCourse(CourseDto d){
        this.dto = d;
    }

    public CourseDto execute() throws BusinessException {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            CourseGateway mg = PersistenceFactory.getCourseGateway();
            DedicationGateway dg = PersistenceFactory.getDedicationsGateway();
            mg.setConnection(c);
            dg.setConnection(c);

            ///////////// Security check for a course ///////////////////////////////
            if (mg.findCourseByCode(dto.code)) {
                c.rollback();
                throw new BusinessException("Course already exists");
            }

            if(dto.startDate.after(dto.endDate)){
                c.rollback();
                throw new BusinessException("Start date must be before end date");
            }

            if(dto.endDate.before(dto.startDate)){
                c.rollback();
                throw new BusinessException("End date must be after start date");
            }

            if(dto.hours < 0){
                c.rollback();
                throw new BusinessException("Hours must be positive");
            }

            for (Object value : dto.percentages.values()) {
                if((Integer)value < 0 || (Integer)value > 100){
                    c.rollback();
                    throw new BusinessException("Percentage must be between 0 and 100");
                }
            }
            ///////////////////////////////////////////////////////////////////////////////
            System.out.println("ANTES DEL ADD");
            mg.add(dto);
            System.out.println("DESPUES DEL ADD");
            c.commit();
            dto.id =  mg.findLastCourse().id;
            System.out.println("ID: "+ dto.id);
            dg.add(dto.percentages, dto.id);
            return dto;
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexion");
        }
    }
}
