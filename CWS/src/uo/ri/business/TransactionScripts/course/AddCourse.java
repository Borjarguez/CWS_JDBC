package uo.ri.business.TransactionScripts.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.course.CourseGateway;
import uo.ri.persistence.dedication.DedicationGateway;

public class AddCourse {
	private CourseDto dto;

	/**
	 * Constructor
	 * 
	 * @param d, the course dto
	 */
	public AddCourse(CourseDto d) {
		this.dto = d;
	}

	/**
	 * Command executor
	 * 
	 * @return the course dto
	 * @throws BusinessException
	 */
	public CourseDto execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			CourseGateway mg = PersistenceFactory.getCourseGateway();
			mg.setConnection(c);

			if (mg.existCourseByCode(dto.code)) {
				c.rollback();
				throw new BusinessException("Course already exists");
			}

			if (dto.startDate.after(dto.endDate)) {
				c.rollback();
				throw new BusinessException("Start date must be before end date");
			}

			if (dto.endDate.before(dto.startDate)) {
				c.rollback();
				throw new BusinessException("End date must be after start date");
			}

			if (dto.hours < 0) {
				c.rollback();
				throw new BusinessException("Hours must be positive");
			}

			for (Object value : dto.percentages.values()) {
				if ((Integer) value < 0 || (Integer) value > 100) {
					c.rollback();
					throw new BusinessException("Percentage must be between 0 and 100");
				}
			}

			mg.add(dto);
			c.commit();
			dto.id = mg.findLastCourse().id;
			addDedication(dto);
			c.commit();

			return dto;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

	/**
	 * Method which adds the dedication to the database
	 * 
	 * @param dto, the course dto
	 */
	private void addDedication(CourseDto dto) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway dg = PersistenceFactory.getDedicationsGateway();
			dg.setConnection(c);

			for (Long key : dto.percentages.keySet()) {
				dg.add(key, dto.percentages.get(key), dto.id);
				c.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQL error");
		}
	}
}
