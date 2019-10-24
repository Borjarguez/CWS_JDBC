package uo.ri.persistence.impl;

import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.VehicleGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VehicleGatewayImpl implements VehicleGateway {
    private Connection c;

    @Override
    public void setConnection(Connection con) {
        this.c = con;
    }

    @Override
    public Optional<VehicleDto> findByPlate(String plate) {
        PreparedStatement pst;
        VehicleDto v;
        String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE");

        try {
            pst = c.prepareStatement(SQL);
            pst.setString(1, plate);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next())
                    return Optional.empty();
                else {
                    v = new VehicleDto();
                    v.id = rs.getLong("id");
                    v.plate = rs.getString("platenumber");
                    v.make = rs.getString("make");
                    v.model = rs.getString("model");
                }
            }
            return Optional.of(v);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
