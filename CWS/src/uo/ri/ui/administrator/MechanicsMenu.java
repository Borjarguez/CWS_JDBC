package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.mechanic.AddMechanicAction;
import uo.ri.ui.administrator.mechanic.DeleteMechanicAction;
import uo.ri.ui.administrator.mechanic.ListMechanicsAction;
import uo.ri.ui.administrator.mechanic.UpdateMechanicAction;

public class MechanicsMenu extends BaseMenu {

	public MechanicsMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", 					AddMechanicAction.class }, 
			{ "Update mechanic", 				UpdateMechanicAction.class }, 
			{ "Delete mechanic", 				DeleteMechanicAction.class }, 
			{ "List mechanics", 				ListMechanicsAction.class },
		};
	}

}
