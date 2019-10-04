package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.administrator.AddMechanic;
import uo.ri.common.BusinessException;
import uo.ri.dto.MechanicDto;

public class AddMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		MechanicDto m = new MechanicDto();
		
		// Get info
		m.dni = Console.readString("Dni"); 
		m.name = Console.readString("Name"); 
		m.surname = Console.readString("Surname");
		
		AddMechanic am = new AddMechanic(m);
		am.execute();
		
		// Print result
		Console.println("Mechanic added");
	}

}
