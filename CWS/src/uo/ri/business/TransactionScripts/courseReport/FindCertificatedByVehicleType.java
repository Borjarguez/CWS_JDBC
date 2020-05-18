package uo.ri.business.TransactionScripts.courseReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.certificate.CertificateGateway;
import uo.ri.persistence.mechanic.MechanicGateway;
import uo.ri.persistence.vehicleType.VehicleTypeGateway;

public class FindCertificatedByVehicleType {

	public List<CertificateDto> execute() {
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway cg = PersistenceFactory.getCertificateGateway();
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			
			cg.setConnection(c);
			mg.setConnection(c);
			vtg.setConnection(c);
			
			List<CertificateDto> certificates = cg.findAllOrdered();
			
			certificates.forEach((certificate) -> {
				certificate.mechanic = mg.findByID(certificate.mechanic.id);
				certificate.vehicleType = vtg.findByID(certificate.vehicleType.id);
			});
			
			return certificates;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}
	
}
