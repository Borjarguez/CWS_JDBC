package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;

public interface CertificateGateway {
	
	void setConnection(Connection con);

	int generateCertificates();

	List<VehicleTypeDto> findVehiclesTypes();

}
