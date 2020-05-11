package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import uo.ri.conf.Conf;
import uo.ri.persistence.DedicationGateway;

public class DedicationGatewayImpl implements DedicationGateway {
    private Connection c;

    @Override
    public void setConnection(Connection con) {
        this.c = con;
    }

    @Override
    public void add(Map<Long, Integer> percentages, Long course_id) {
        String SQL = Conf.getInstance().getProperty("SQL_ADD_DEDICATION");

        try (PreparedStatement pst = c.prepareStatement(SQL)) {
            for (Map.Entry<Long, Integer> entry : percentages.entrySet()) {
                pst.setLong(1, course_id);
                pst.setLong(2, entry.getKey());
                pst.setInt(3, entry.getValue());

                pst.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long dedication_id) {

    }
}
