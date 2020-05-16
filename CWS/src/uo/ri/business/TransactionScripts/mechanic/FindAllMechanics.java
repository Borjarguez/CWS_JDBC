package uo.ri.business.TransactionScripts.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.mechanic.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindAllMechanics {

    public List<MechanicDto> execute() {
        try(Connection c = Jdbc.getConnection()){
            c.setAutoCommit(false);
            MechanicGateway mg = PersistenceFactory.getMechanicGateway();
            mg.setConnection(c);
            return mg.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
