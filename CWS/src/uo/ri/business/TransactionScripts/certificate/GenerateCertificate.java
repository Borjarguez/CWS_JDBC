package uo.ri.business.TransactionScripts.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.certificate.CertificateGateway;
import uo.ri.persistence.course.CourseGateway;
import uo.ri.persistence.courseAttendance.CourseAttendanceGateway;
import uo.ri.persistence.mechanic.MechanicGateway;
import uo.ri.persistence.vehicleType.VehicleTypeGateway;

public class GenerateCertificate {
	private CourseGateway cgt = PersistenceFactory.getCourseGateway();
	private VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
	private CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
	private MechanicGateway mg = PersistenceFactory.getMechanicGateway();
	private CertificateGateway cg = PersistenceFactory.getCertificateGateway();

	public int execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			mg.setConnection(c);
			cgt.setConnection(c);
			vtg.setConnection(c);
			cag.setConnection(c);
			cg.setConnection(c);
			c.setAutoCommit(false);

			// Different lists needed for the method
			List<VehicleTypeDto> vehiclesTypes = vtg.findAll();
			List<MechanicDto> passedMehanics = mg.findPassedMechanics();

			// Number of certificates generated
			int numCertificates = 0;

			for (VehicleTypeDto v : vehiclesTypes) {
				for (MechanicDto m : passedMehanics) {
					if (!cg.doesCertificateExist(m.id, v.id)) {
						List<Long> courses_ids = cgt.findCourseByMechanicIdVehicleTypeId(v.id, m.id);
						int totalHours = 0;
						for (Long id : courses_ids) {
							totalHours += (cgt.findCourseByID(id).hours
									* cgt.getPercentageByVehicleTypeID(id, v.id) / 100)
									* cag.findAttendanceByCourseIDMechanicID(id, m.id).attendance / 100;
						}

						if (totalHours >= v.minTrainigHours) {
							saveCertificates(v.id, m.id);
							numCertificates++;
						}
					}
				}
			}

			c.commit();
			return numCertificates;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}

	private void saveCertificates(Long idVehicleType, Long idMechanic) throws BusinessException {		
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);

			if (mg.findByID(idMechanic) == null) {
				c.rollback();
				throw new BusinessException("Mechanic doesn't exist");
			}

			if (vtg.findByID(idVehicleType) == null) {
				c.rollback();
				throw new BusinessException("Vehicle type doesn't exist");
			}

			cg.generateCertificates(idMechanic, idVehicleType);
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
