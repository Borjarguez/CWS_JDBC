package uo.ri.business.TransactionScripts.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.mechanic.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteMechanic {
	private long idMechanic;

	public DeleteMechanic(long idMechanic) {
		this.idMechanic = idMechanic;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			
			// Security check for a mechanic
			if (mg.findByID(idMechanic) == null) {
				c.rollback();
				throw new BusinessException("Mechanic already exists");
			}
			//////////////////////////////////////
			
			mg.delete(idMechanic);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
