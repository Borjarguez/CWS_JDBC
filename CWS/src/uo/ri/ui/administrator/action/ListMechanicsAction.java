package uo.ri.ui.administrator.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.impl.MechanicCrudServiceImpl;
import uo.ri.common.BusinessException;
import uo.ri.dto.MechanicDto;
import uo.ri.ui.util.Printer;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n");
		
		MechanicCrudService mcd = new MechanicCrudServiceImpl();
		List<MechanicDto> list = mcd.findAllMechanics();
		
		for(MechanicDto m : list) {
			Printer.printMechanic(m);
		}
		
		
	}
}
