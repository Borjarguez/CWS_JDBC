package uo.ri.ui.foreman.reception;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.reception.actions.*;

public class ReceptionMenu extends BaseMenu {

	public ReceptionMenu() {
		menuOptions = new Object[][] { 
			{"Foreman > Vehicle reception", null},
			
			{"Register a work order", 	 RegisterWorkOrderAction.class }, 
			{"Update a work order", 	 UpdateWorkOrderAction.class },
			{"Remove a work order", 	 RemoveWorkOrderAction.class },
			{"", null},
			{"List unfinished work orders", ListUnfinishedWorkOrdersAction.class }, 
			{"List work orders by plate", ListWorkOrdersByPlateNumberAction.class }, 
			{"View work order detail", 	 ViewWorkOrderDetailAction.class },
			{"", null},
			{"List certified mechanics", ListCertifiedMechanicsAction.class }, 
			{"Assign a work order",  	 AssignWorkOrderAction.class },
		};
	}

}
