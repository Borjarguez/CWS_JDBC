package uo.ri.ui.administrator.training.attendance.actions;

import alb.util.menu.Action;
import uo.ri.business.ServiceLayer.training.CourseAttendanceService;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

import java.util.List;

public class ListAttendanceToCourseAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();

	@Override
	public void execute() throws Exception {
		Long cId = user.askForCourseId();

		CourseAttendanceService s = ServiceFactory.getCourseAttendanceService();
		List<EnrollmentDto> attendance = s.findAttendanceByCourseId( cId );

		attendance.forEach( att -> Printer.printAttendingMechanic(att) );
	}

}
