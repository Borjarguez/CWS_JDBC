package uo.ri.persistence;

import java.sql.Connection;

public interface CertificateGateway {
	
	/**
	 * Method which sets the connection
	 * @param con
	 */
	void setConnection(Connection con);

	/**
	 * Method which generates the certificates
	 * @param idMechanic
	 * @param idVehicleType
	 */
	void generateCertificates(Long idMechanic, Long idVehicleType);
	
	/**
	 * 
	 * @param idMechanic
	 * @param idVehicle
	 * @return
	 */
	boolean doesCertificateExist(Long idMechanic, Long idVehicle);

}
