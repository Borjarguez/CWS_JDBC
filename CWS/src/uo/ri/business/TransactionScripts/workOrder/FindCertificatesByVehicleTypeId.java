package uo.ri.business.TransactionScripts.workOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.certificate.CertificateGateway;
import uo.ri.persistence.mechanic.MechanicGateway;
import uo.ri.persistence.vehicleType.VehicleTypeGateway;

public class FindCertificatesByVehicleTypeId {
	private Long id;

	public FindCertificatesByVehicleTypeId(Long id) {
		this.id = id;
	}

	/**
	 * Buscar certificado por tipo de vehículo
	 * 
	 * @return lista de certificados
	 */
	public List<CertificateDto> execute() {
		try (Connection c = Jdbc.getConnection()) {
			CertificateGateway cg = PersistenceFactory.getCertificateGateway();
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();

			cg.setConnection(c);
			vtg.setConnection(c);
			mg.setConnection(c);

			List<CertificateDto> result = cg.findCertificatesByVehicleTypeId(id);

			result.forEach((ce) -> {
				ce.mechanic = mg.findByID(ce.mechanic.id);
				ce.vehicleType = vtg.findByID(ce.vehicleType.id);
			});

			return result;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

}
