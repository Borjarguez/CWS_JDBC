package uo.ri.business.TransactionScripts.vehicle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.VehicleGateway;

public class FindVehicleByPlate {
    private String plate;

    public FindVehicleByPlate(String plate){
        this.plate = plate;
    }

    public Optional<VehicleDto> execute() {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            VehicleGateway vg = PersistenceFactory.getVehicleGateway();
            vg.setConnection(c);
            return vg.findByPlate(plate);
        } catch (SQLException e) {
            throw new RuntimeException("Connection error");
        }
    }
}
