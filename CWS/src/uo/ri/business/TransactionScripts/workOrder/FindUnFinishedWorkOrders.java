package uo.ri.business.TransactionScripts.workOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.workOrder.WorkOrderGateway;

public class FindUnFinishedWorkOrders {
	
	public List<WorkOrderDto> execute() {
		try (Connection c = Jdbc.getConnection()) {
			WorkOrderGateway mg = PersistenceFactory.getWorkOrderGateway();
			mg.setConnection(c);
			return mg.findUnFinishedWorkOrders();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}
}
