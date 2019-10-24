package uo.ri.persistence;

import uo.ri.business.dto.MechanicDto;

import java.sql.Connection;
import java.util.List;

public interface MechanicGateway {
	
    void setConnection(Connection con);
	
	void add(MechanicDto m);

	void delete(Long idMechanic);

	void update(MechanicDto m);

	List<MechanicDto> findAll();

	MechanicDto findByID(Long idMechanic);

	MechanicDto findByDNI(String dni);
	
	List<MechanicDto> findPassedMechanics();
}
