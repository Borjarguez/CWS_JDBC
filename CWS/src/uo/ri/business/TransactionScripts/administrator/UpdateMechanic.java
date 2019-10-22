package uo.ri.business.TransactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class UpdateMechanic {
	private MechanicDto m;

	public UpdateMechanic(MechanicDto m) {
		this.m = m;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection()) {
			c.setAutoCommit(false);
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);

			// Security check for a mechanic

			if (mg.findByDNI(m.dni) == null) {
				c.rollback();
				throw new BusinessException("Mechanic doesn't exists");
			}

			//////////////////////////////////////
			mg.update(m);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
