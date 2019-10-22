package uo.ri.persistence;

import java.sql.Connection;

public interface CertificateGateway {
	
	void setConnection(Connection con);

	void generateCertificates(Long idMechanic, Long idVehicleType);
	
	boolean doesCertificateExist(Long idMechanic, Long idVehicle);

}
