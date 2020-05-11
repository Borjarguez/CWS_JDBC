package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.WorkOrderGateway;

public class WorkOrderGatewayImpl implements WorkOrderGateway {
    private Connection c;

    @Override
    public void setConnection(Connection con) {
        this.c = con;
    }

    @Override
    public void add(WorkOrderDto w) {
        String SQL = Conf.getInstance().getProperty("SQL_ADD_WORKORDER");

        try (PreparedStatement pst = c.prepareStatement(SQL)) {
            pst.setLong(1, w.vehicleId);
            pst.setString(2, w.description);
            pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pst.setString(4, "OPEN");
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long workOrder_id) {
        String SQL = Conf.getInstance().getProperty("SQL_DELETE_WORKORDER");

        try (PreparedStatement pst = c.prepareStatement(SQL)) {
            pst.setLong(1, workOrder_id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(WorkOrderDto m) {
        String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER");

        try (PreparedStatement pst = c.prepareStatement(SQL)) {
            pst.setString(1, m.description);
            pst.setLong(2, m.id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WorkOrderDto findLastWorkOrder() {
        PreparedStatement pst;
        WorkOrderDto v;
        String SQL = Conf.getInstance().getProperty("SQL_FIND_LAST_WORKORDER");

        try {
            pst = c.prepareStatement(SQL);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next())
                    return null;
                else
                    v = findById(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return v;
    }

    @Override
    public WorkOrderDto findById(Long workOrder_id) {
        WorkOrderDto v;
        ResultSet rs;
        String SQL = Conf.getInstance().getProperty("SQL_FIND_WORKORDER_ID");

        try (PreparedStatement pst = c.prepareStatement(SQL);) {
            pst.setLong(1, workOrder_id);
            rs = pst.executeQuery();

            if (!rs.next())
                return null;
            else {
                v = new WorkOrderDto();
                v.id = rs.getLong("id");
                v.date = rs.getTimestamp("date");
                v.description = rs.getString("description");
                v.status = rs.getString("status");
            }
            return v;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findInterventions(Long workOrder_id) {
        ResultSet rs;
        String SQL = Conf.getInstance().getProperty("SQL_FIND_WORKORDER_INTERVENTIONS");

        try (PreparedStatement pst = c.prepareStatement(SQL);) {
            pst.setLong(1, workOrder_id);
            rs = pst.executeQuery();

            if (!rs.next())
                return false;
            else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
