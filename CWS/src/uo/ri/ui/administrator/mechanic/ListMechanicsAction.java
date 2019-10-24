package uo.ri.ui.administrator.mechanic;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.mechanic.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

import java.util.List;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n");
		
		MechanicCrudService mcd = ServiceFactory.getMechanicCrudService();
		List<MechanicDto> list = mcd.findAllMechanics();
		
		for(MechanicDto m : list) {
			Printer.printMechanic(m);
		}
		
		
	}
}
