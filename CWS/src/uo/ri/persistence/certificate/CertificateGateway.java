package uo.ri.persistence.certificate;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.CertificateDto;

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

	/**
	 * Method which finds certificates ordered by vehicle type
	 * @return
	 */
	List<CertificateDto> findAllOrdered();


    /**
     * Method which searches for the certificates
     * @param id, the vehicle type id
     * @return the list of certificates
     */
	List<CertificateDto> findCertificatesByVehicleTypeId(Long id);

}
