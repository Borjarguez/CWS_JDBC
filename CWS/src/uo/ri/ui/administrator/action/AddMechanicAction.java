package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		MechanicDto m = new MechanicDto();
		
		// Get info
		m.dni = Console.readString("Dni"); 
		m.name = Console.readString("Name"); 
		m.surname = Console.readString("Surname");
		
		MechanicCrudService mcd = ServiceFactory.getMechanicCrudService();
		mcd.addMechanic(m);
		
		// Print result
		Console.println("Mechanic added");
	}

}
