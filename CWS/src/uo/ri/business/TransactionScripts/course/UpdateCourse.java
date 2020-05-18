package uo.ri.business.TransactionScripts.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.course.CourseGateway;

public class UpdateCourse {
	private CourseDto dto;

	public UpdateCourse(CourseDto dto) {
		this.dto = dto;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);

			if (cg.findCourseByID(dto.id) == null) {
				c.rollback();
				throw new BusinessException("Course doesn't exist");
			}
			
			if (checkNullValues(dto)) {
				c.rollback();
				throw new BusinessException("Some parameter is null");
			} 
			
			if (dto.startDate.before(new Date())) {
				c.rollback();
				throw new BusinessException("Course has been imparted at any time");
			}

			cg.update(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean checkNullValues(CourseDto dto) {
		return dto.code == null || dto.description == null || dto.startDate == null || dto.endDate == null
				|| Integer.valueOf(dto.hours) == null || dto.name == null;
	}
}
