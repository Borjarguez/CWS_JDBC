package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.MechanicCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMechanic = Console.readLong("Type mechanic id "); 
		
		MechanicCrudService mcd = ServiceFactory.getMechanicCrudService();
		mcd.deleteMechanic(idMechanic);
		
		Console.println("Mechanic deleted");
	}

}
