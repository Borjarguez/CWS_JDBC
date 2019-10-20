package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String SQL = Conf.getInstance().getProperty("SQL_ADD_MECHANIC");

		try (PreparedStatement pst = c.prepareStatement(SQL)){ 
			pst.setString(1, m.dni);
			pst.setString(2, m.name);
			pst.setString(3, m.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long idMechanic) {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_MECHANIC");

		try (PreparedStatement pst =  c.prepareStatement(SQL)){
			pst.setLong(1, idMechanic);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(MechanicDto m) {
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_MECHANIC");

		try (PreparedStatement pst = c.prepareStatement(SQL)){
			pst.setString(1, m.name);
			pst.setString(2, m.surname);
			pst.setLong(3, m.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MechanicDto> findAll() {
		List<MechanicDto> list = new ArrayList<MechanicDto>();
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_MECHANIC");

		try(PreparedStatement pst = c.prepareStatement(SQL); ResultSet rs = pst.executeQuery();) {

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
		}
		return list;
	}

	@Override
	public MechanicDto findByID(Long idMechanic) {
		MechanicDto m = null;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_ID");

		try (PreparedStatement pst = c.prepareStatement(SQL); ResultSet rs = pst.executeQuery();){
			
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
		} 
	}

	@Override
	public MechanicDto findByDNI(String dni) {
		MechanicDto m = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_DNI");

		try (PreparedStatement pst = c.prepareStatement(SQL);){
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
		}
	}



}
