package uo.ri.business.TransactionScripts.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.mechanic.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class AddMechanic {
    private MechanicDto m;

    public AddMechanic(MechanicDto m) {
        this.m = m;
    }

    public void execute() throws BusinessException {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            MechanicGateway mg = PersistenceFactory.getMechanicGateway();
            mg.setConnection(c);

            // Security check for a mechanic

            if (mg.findByDNI(m.dni) != null) {
                c.rollback();
                throw new BusinessException("Mechanic already exists");
            }
            //////////////////////////////////////
            mg.add(m);
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexion");
        }

    }
}
