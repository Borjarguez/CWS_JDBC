package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

public interface DedicationGateway {
	
	/**
	 * Method which sets the connection
	 * @param con
	 */
    void setConnection(Connection con);

    /**
     * Method which adds the element to the database
     * @param e
     */
    void add(Map<Long, Integer> percentages, Long course_id);

    /**
     * Method which deletes
     * @param dedication_id
     */
    void delete(Long dedication_id);
}
