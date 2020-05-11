package uo.ri.business.ServiceLayer.training.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.business.ServiceLayer.training.CourseCrudService;
import uo.ri.business.TransactionScripts.course.AddCourse;
import uo.ri.business.TransactionScripts.course.DeleteCourse;
import uo.ri.business.TransactionScripts.course.FindAllCourses;
import uo.ri.business.TransactionScripts.course.FindCourseByID;
import uo.ri.business.TransactionScripts.course.UpdateCourse;
import uo.ri.business.TransactionScripts.vehicleType.ListVehicleTypes;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;

public class CourseCrudServiceImpl implements CourseCrudService {

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		AddCourse ad = new AddCourse(dto);
		return ad.execute();
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		UpdateCourse up = new UpdateCourse(dto);
		up.execute();
	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		DeleteCourse dl = new DeleteCourse(id);
		dl.execute();
	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		FindAllCourses fl = new FindAllCourses();
		return fl.execute();
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		ListVehicleTypes vl = new ListVehicleTypes();
		return vl.execute();
	}

	@Override
	public Optional<CourseDto> findCourseById(Long cId) throws BusinessException {
		FindCourseByID fi = new FindCourseByID(cId);
		return fi.execute();
	}

}
