package uo.ri.persistence.workOrder;

import uo.ri.business.dto.WorkOrderDto;

import java.sql.Connection;

public interface WorkOrderGateway {
	
	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection con);

    /**
     * Method which adds the element to the database
     * @param m
     */
    void add(WorkOrderDto m);

    /**
     * Method which deletes the element from the database
     * @param workOrder_id
     */
    void delete(Long workOrder_id);

    /**
     * Method which updates the work order
     * @param m
     */
    void update(WorkOrderDto m);

    /**
     * Method which finds the last work order
     * @return
     */
    WorkOrderDto findLastWorkOrder();

    /**
     * Method which finds the work order by its id
     * @param workOrder_id
     * @return
     */
    WorkOrderDto findById(Long workOrder_id);

    /**
     * Method which finds the interventions 
     * @param workOrder_id
     * @return true if it has interventions, false in other case
     */
    boolean findInterventions(Long workOrder_id);
}
