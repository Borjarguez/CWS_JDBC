package uo.ri.ui.administrator.training.attendance.actions;

import alb.util.console.Console;
import uo.ri.business.ServiceLayer.mechanic.MechanicCrudService;
import uo.ri.business.ServiceLayer.training.CourseAttendanceService;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

import java.util.List;

public class AttendanceUserInteractor {

	public void fill(EnrollmentDto att) throws BusinessException {
		fillCourseId(att);
		fillMechanicId(att);
		fillAttendance(att);
		fillPassed( att);
	}

	private void fillAttendance(EnrollmentDto att) {
		att.attendance = Console.readInt("% of attendance");
	}

	private void fillMechanicId(EnrollmentDto att) throws BusinessException {
		showMechanics();
		att.mechanicId = Console.readLong("Mechanic id");
	}

	private void fillCourseId(EnrollmentDto att) throws BusinessException {
		showCourses();
		att.courseId = Console.readLong("Course id");
	}

	private void fillPassed(EnrollmentDto att) {
		att.passed = Console.readString("Passed (y/n)?").equalsIgnoreCase("y");
	}

	private void showMechanics() throws BusinessException {
		MechanicCrudService cs = ServiceFactory.getMechanicCrudService();
		List<MechanicDto> mechanics = cs.findAllMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m) );
	}

	public void showCourses() throws BusinessException {
		CourseAttendanceService cs = ServiceFactory.getCourseAttendanceService();
		List<CourseDto> mechanics = cs.findAllActiveCourses();
		Console.println("List of courses");
		mechanics.forEach((c) -> Printer.printCourse(c) );
	}

	public Long askForCourseId() throws BusinessException {
		showCourses();
		return Console.readLong("Course id");
	}

}
