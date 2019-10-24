package uo.ri.ui.administrator.mechanic;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.mechanic.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		MechanicDto m = new MechanicDto();
		// Get info
		m.id = Console.readLong("Type mechahic id to update"); 
		m.name = Console.readString("Name"); 
		m.surname = Console.readString("Surname");
		
		MechanicCrudService mcd = ServiceFactory.getMechanicCrudService();
		mcd.updateMechanic(m);
		
		// Print result
		Console.println("Mechanic updated");
	}

}
