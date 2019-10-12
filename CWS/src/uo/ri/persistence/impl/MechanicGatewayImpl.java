package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.MechanicGateway;

public class MechanicGatewayImpl implements MechanicGateway {
	private Connection c;

	@Override
	public void setConnection(Connection con) {
		this.c = con;
	} 
	
	@Override
	public void add(MechanicDto m) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_ADD_MECHANIC");

		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, m.dni);
			pst.setString(2, m.name);
			pst.setString(3, m.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

	@Override
	public void delete(Long idMechanic) {
//		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_MECHANIC");

		try {
//			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

	@Override
	public void update(MechanicDto m) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MechanicDto> findAll() {
		List<MechanicDto> list = new ArrayList<MechanicDto>();

		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_MECHANIC");

		try {
//			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);

			rs = pst.executeQuery();

			while (rs.next()) {
				MechanicDto m = new MechanicDto();
				m.id = rs.getLong("id");
				m.name = rs.getString("name");
				m.dni = rs.getString("dni");
				m.surname = rs.getString("surname");
				list.add(m);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

		return list;
	}

	@Override
	public MechanicDto findByID(Long idMechanic) {
		MechanicDto m = null;
//		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_ID");

		try {
//			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			if (!rs.next())
				return m;
			else {
				m = new MechanicDto();
				m.id = rs.getLong("id");
				m.name = rs.getString("name");
				m.dni = rs.getString("dni");
				m.surname = rs.getString("surname");
			}

			return m;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

	}

	@Override
	public MechanicDto findByDNI(String dni) {
		MechanicDto m = null;
//		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_DNI");

		try {
//			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			if (!rs.next())
				return m;
			else {
				m = new MechanicDto();
				m.id = rs.getLong("id");
				m.name = rs.getString("name");
				m.dni = rs.getString("dni");
				m.surname = rs.getString("surname");
			}

			return m;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

	}



}
