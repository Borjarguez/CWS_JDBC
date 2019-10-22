package uo.ri.business.TransactionScripts.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CertificateGateway;
import uo.ri.persistence.CourseAttendanceGateway;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.VehicleTypeGateway;

public class GenerateCertificate {
	private CourseGateway cgt = PersistenceFactory.getCourseGateway();
	private VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
	private CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
	private MechanicGateway mg = PersistenceFactory.getMechanicGateway();
	private CertificateGateway cg = PersistenceFactory.getCertificateGateway();

	public int execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			
			c.setAutoCommit(false);
			mg.setConnection(c);
			cgt.setConnection(c);
			vtg.setConnection(c);
			cag.setConnection(c);

			// Different lists needed for the method
			List<VehicleTypeDto> vehiclesTypes = vtg.findAll();
			List<MechanicDto> passedMehanics = mg.findPassedMechanics();

			// Number of certificates generated
			int numCertificates = 0;

			for (VehicleTypeDto v : vehiclesTypes) {
				for (MechanicDto m : passedMehanics) {
					if (!checkExistCertificate(m.id, v.id)) {
						List<Long> courses_ids = findCoursesIDsByMechanicAndVehicleTypeIDs(v.id,m.id);
						int totalHours = 0;
						for (Long id : courses_ids) {
							System.out.println(v.id +" "+m.id+" "+id);
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

	private List<Long> findCoursesIDsByMechanicAndVehicleTypeIDs(Long vehicleType_id, Long mechanic_id) {
		
		List<Long> ids = new ArrayList<Long>();
		
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			cgt.setConnection(c);
			ids = cgt.findCourseByMechanicIdVehicleTypeId(vehicleType_id, mechanic_id);
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ids;
	}

	private boolean checkExistCertificate(Long idMechanic, Long idVehicleType) {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			cg.setConnection(c);

			c.commit();
			return cg.doesCertificateExist(idMechanic, idVehicleType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
