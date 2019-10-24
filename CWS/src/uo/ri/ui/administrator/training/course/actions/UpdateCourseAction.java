package uo.ri.ui.administrator.training.course.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.training.CourseCrudService;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

import java.util.Optional;

public class UpdateCourseAction implements Action {

	private CourseUserInteractor user = new CourseUserInteractor();

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		Long cId = Console.readLong("Course id");
		CourseCrudService cs = ServiceFactory.getCourseCrudService();
		Optional<CourseDto> oc = cs.findCourseById( cId );
		assertPresent( oc );

		CourseDto c = oc.get();
		user.fill( c );

		// Invoke the service
		cs.updateCourse( c );

		// Show result
		Console.println("Course updated");
	}

	private void assertPresent(Optional<CourseDto> oc) throws BusinessException {
		if ( oc.isPresent() ) return;
		throw new BusinessException("Entity not found");
	}

}
