package uo.ri.ui.administrator.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.administrator.ListMechanic;
import uo.ri.common.BusinessException;
import uo.ri.dto.MechanicDto;
import uo.ri.ui.util.Printer;

public class ListMechanicsAction implements Action {

	
	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n");
		ListMechanic lm = new ListMechanic();
		List<MechanicDto> list = lm.execute();
		
		for(MechanicDto m : list) {
			Printer.printMechanic(m);
		}
		
		
	}
}
