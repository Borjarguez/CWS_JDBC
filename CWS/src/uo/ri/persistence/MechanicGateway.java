package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {
	
    void setConnection(Connection con);
	
	void add(MechanicDto m);

	void delete(Long idMechanic);

	void update(MechanicDto m);

	List<MechanicDto> findAll();

	MechanicDto findByID(Long idMechanic);

	MechanicDto findByDNI(String dni);
}
