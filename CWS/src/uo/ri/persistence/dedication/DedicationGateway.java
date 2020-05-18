package uo.ri.persistence.dedication;

import java.sql.Connection;
import java.util.List;

public interface DedicationGateway {
	
	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection con);
    
    /**
     * Method which registers the dedication in the database
     * @param typeID, the vehicle type id
     * @param percentage, the percentage
     * @param courseID, the course id
     */
    public void add(Long typeID, Integer percentage, Long courseID);

    /**
     * Method which deletes
     * @param dedication_id
     */
    //void delete(Long dedication_id);

    /**
     * Method which deletes the dedication by course id
     * @param course_id
     */
	void deleteByCourseID(Long course_id);

	/**
	 * Method which finds the courses's IDs
	 * @param mechanicID, the mechanic ID
	 * @param vehicleTypeID, the vehicle type ID
	 * @return the list
	 */
	List<Long> findCoursesByMechanicVehicleType(Long mechanicID, Long vehicleTypeID);
}
