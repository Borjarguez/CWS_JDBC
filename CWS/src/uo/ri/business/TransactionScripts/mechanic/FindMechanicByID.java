package uo.ri.business.TransactionScripts.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class FindMechanicByID {
    private Long idMechanic;

    public FindMechanicByID(Long idMechanic) {
        this.idMechanic = idMechanic;
    }

    public MechanicDto execute() {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            MechanicGateway mg = PersistenceFactory.getMechanicGateway();
            mg.setConnection(c);
            return mg.findByID(idMechanic);
        } catch (SQLException e) {
            throw new RuntimeException("Connection error");
        }
    }

}
