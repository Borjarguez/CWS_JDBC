package uo.ri.ui.administrator.training.reports;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.training.reports.actions.ListTrainingOfMechanicAction;

public class ReportsMenu extends BaseMenu {

	public ReportsMenu() {
		menuOptions = new Object[][] {
			{ "Manager > Training management > Reports", null },

			{ "Training of a mechanic",			ListTrainingOfMechanicAction.class },
			{ "Training by vehicle types",		NotYetImplementedAction.class },
			{ "Certifications by vehicle type",	NotYetImplementedAction.class }
		};
	}

}
