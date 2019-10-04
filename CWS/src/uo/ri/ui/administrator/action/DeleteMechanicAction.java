package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.administrator.DeleteMechanic;
import uo.ri.common.BusinessException;

public class DeleteMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		Long idMechanic = Console.readLong("Type mechanic id "); 
		
		DeleteMechanic dl = new DeleteMechanic(idMechanic);
		dl.execute();
		
		Console.println("Mechanic deleted");
	}

}
