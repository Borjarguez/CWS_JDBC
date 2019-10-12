package uo.ri.business.TransactionScripts.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;

public class UpdateMechanic {
	private static String SQL = "update TMechanics " + "set name = ?, surname = ? " + "where id = ?";
	private MechanicDto m;
	
	
	public UpdateMechanic(MechanicDto m) {
		this.m = m;
	}

	public void execute() {
		
	}
}
