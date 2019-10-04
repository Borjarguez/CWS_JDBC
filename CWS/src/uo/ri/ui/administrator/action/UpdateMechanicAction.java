package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.impl.MechanicCrudServiceImpl;
import uo.ri.common.BusinessException;
import uo.ri.dto.MechanicDto;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		MechanicDto m = new MechanicDto();
		// Get info
		m.id = Console.readLong("Type mechahic id to update"); 
		m.name = Console.readString("Name"); 
		m.surname = Console.readString("Surname");
		
		MechanicCrudService mcd = new MechanicCrudServiceImpl();
		mcd.updateMechanic(m);
		
		// Print result
		Console.println("Mechanic updated");
	}

}
