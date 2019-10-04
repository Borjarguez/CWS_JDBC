package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String dni = Console.readString("Dni"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		
		
		// Print result
		Console.println("Mechanic added");
	}

}
