package uo.ri.persistence.mechanic;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.TrainingForMechanicRow;

import java.sql.Connection;
import java.util.List;

public interface MechanicGateway {
	
	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection con);
	
    /**
     * Method which adds the element to the database
     * @param e
     */
	void add(MechanicDto m);

	/**
	 * Method which deletes a mechanic
	 * @param idMechanic
	 */
	void delete(Long idMechanic);

	/**
	 * Method which updates a mechanic
	 * @param m
	 */
	void update(MechanicDto m);

	/**
	 * Method which finds all the mechanic
	 * @return
	 */
	List<MechanicDto> findAll();

	/**
	 * Method which finds the mechanic by its id
	 * @param idMechanic
	 * @return
	 */
	MechanicDto findByID(Long idMechanic);

	/**
	 * Method which finds a mechanic by its DNI
	 * @param dni
	 * @return
	 */
	MechanicDto findByDNI(String dni);
	
	/**
	 * Method which finds the passed mechanics
	 * @return
	 */
	List<MechanicDto> findPassedMechanics();

	/**
	 * Method which finds
	 * @param mechanic_id
	 * @return
	 */
    List<TrainingForMechanicRow> findTrainingForMechanic(Long mechanic_id);
}
