package uo.ri.business.TransactionScripts.courseReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.course.CourseGateway;
import uo.ri.persistence.courseAttendance.CourseAttendanceGateway;
import uo.ri.persistence.dedication.DedicationGateway;
import uo.ri.persistence.mechanic.MechanicGateway;
import uo.ri.persistence.vehicleType.VehicleTypeGateway;

public class FindTrainingByVehicleTypeAndMechanic {

	public List<TrainingHoursRow> execute() {
		List<TrainingHoursRow> result = new ArrayList<TrainingHoursRow>();

		findAllVehicleTypes().forEach((vt) -> {
			findAllMechanics().forEach((m) -> {
				List<Long> coursesIDs = findCoursesByMechanicVehicleType(m.id, vt.id);

				if (coursesIDs.size() > 0) {
					TrainingHoursRow row = new TrainingHoursRow();

					coursesIDs.forEach((id) -> {
						int hours = findCourseById(id).hours;
						int attendance = findAttendedHoursByMechanicIDCourseID(id, m.id);
						row.enrolledHours += (int) (attendance * 0.01 * hours);
					});

					row.mechanicFullName = m.name + " " + m.surname;
					row.vehicleTypeName = vt.name;
					
					result.add(row);
				}
			});
		});

		return result;

	}

	/**
	 * Method which finds the attendance of a mechanic to a course
	 * 
	 * @param courseID,   the course ID
	 * @param mechanicID, the mechanic ID
	 * @return the attendance
	 */
	private int findAttendedHoursByMechanicIDCourseID(Long courseID, Long mechanicID) {
		try (Connection c = Jdbc.getConnection()) {
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			cag.setConnection(c);

			return cag.findAttendedHoursByMechanicIDCourseID(courseID, mechanicID);
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR");
		}
	}

	/**
	 * Method which searches the database for the course
	 * 
	 * @param courseID, the course id
	 * @return the course DTO
	 */
	private CourseDto findCourseById(Long courseID) {
		try (Connection c = Jdbc.getConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);

			return cg.findCourseByID(courseID);
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR");
		}
	}

	/**
	 * Method which finds the courses's IDs
	 * 
	 * @param mechanicID,    the mechanic ID
	 * @param vehicleTypeID, the vehicle type ID
	 * @return the list
	 */
	private List<Long> findCoursesByMechanicVehicleType(Long mechanicID, Long vehicleTypeID) {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway dg = PersistenceFactory.getDedicationsGateway();
			dg.setConnection(c);

			return dg.findCoursesByMechanicVehicleType(mechanicID, vehicleTypeID);
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR");
		}
	}

	/**
	 * Method which returns the mechanics from the database
	 * 
	 * @return the mechanics
	 */
	private List<MechanicDto> findAllMechanics() {
		try (Connection c = Jdbc.getConnection()) {
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);

			return mg.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR");
		}
	}

	/**
	 * Method which returns the mechanics from the database
	 * 
	 * @return the mechanics
	 */
	private List<VehicleTypeDto> findAllVehicleTypes() {
		try (Connection c = Jdbc.getConnection()) {
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			vtg.setConnection(c);
			return vtg.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("Connection ERROR");
		}
	}

}
