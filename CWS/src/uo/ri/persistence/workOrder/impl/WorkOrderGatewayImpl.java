package uo.ri.persistence.workOrder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.workOrder.WorkOrderGateway;

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
		WorkOrderDto w;
		ResultSet rs;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_WORKORDER_ID");

		try (PreparedStatement pst = c.prepareStatement(SQL);) {
			pst.setLong(1, workOrder_id);
			rs = pst.executeQuery();

			if (!rs.next())
				return null;
			else {
				w = new WorkOrderDto();
				w.id = rs.getLong("id");
				w.total = rs.getDouble("amount");
				w.description = rs.getString("description");
				w.status = rs.getString("status");
				w.invoiceId = rs.getLong("invoice_id");
				w.mechanicId = rs.getLong("mechanic_id");
				w.vehicleId = rs.getLong("vehicle_id");
				w.date = rs.getTimestamp("date");
			}
			return w;

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

	@Override
	public List<WorkOrderDto> findUnFinishedWorkOrders() {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_UNFINISHED_WORKORDERS");

		PreparedStatement pst = null;
		ResultSet rs = null;

		List<WorkOrderDto> result = new ArrayList<WorkOrderDto>();

		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			WorkOrderDto wo = null;
			while (rs.next()) {
				wo = new WorkOrderDto();

				wo.id = rs.getLong("id");
				wo.total = rs.getDouble("amount");
				wo.description = rs.getString("description");
				wo.status = rs.getString("status");
				wo.date = rs.getTimestamp("date");
				wo.invoiceId = rs.getLong("invoice_id");
				wo.mechanicId = rs.getLong("mechanic_id");
				wo.vehicleId = rs.getLong("vehicle_id");

				result.add(wo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(Long id) {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_WORKORDERS_VEHICLE_ID");

		PreparedStatement pst = null;
		ResultSet rs = null;

		List<WorkOrderDto> result = new ArrayList<WorkOrderDto>();

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			WorkOrderDto wo = null;
			while (rs.next()) {
				wo = new WorkOrderDto();

				wo.id = rs.getLong("id");
				wo.total = rs.getDouble("amount");
				wo.description = rs.getString("description");
				wo.status = rs.getString("status");
				wo.date = rs.getTimestamp("date");
				wo.invoiceId = rs.getLong("invoice_id");
				wo.mechanicId = rs.getLong("mechanic_id");
				wo.vehicleId = rs.getLong("vehicle_id");

				result.add(wo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}
}
