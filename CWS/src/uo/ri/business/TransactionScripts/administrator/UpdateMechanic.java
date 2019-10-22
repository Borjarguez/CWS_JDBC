package uo.ri.business.TransactionScripts.administrator;

import uo.ri.business.dto.MechanicDto;

public class UpdateMechanic {
	private static String SQL_UPDATE_MECHANIC = "update TMechanics set name = ?, surname = ? where id = ?";
	private MechanicDto m;
	
	
	public UpdateMechanic(MechanicDto m) {
		this.m = m;
	}

	public void execute() {
		
	}
}
