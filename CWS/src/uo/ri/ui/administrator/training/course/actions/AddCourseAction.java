package uo.ri.ui.administrator.training.course.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.training.CourseCrudService;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class AddCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		CourseDto c = new CourseDto();
		user.fill( c );

		// Invoke the service
		CourseCrudService cs = ServiceFactory.getCourseCrudService();
		cs.registerNew(c);

		// Show result
		Console.println("New course registered: " + c.id);
	}

}
