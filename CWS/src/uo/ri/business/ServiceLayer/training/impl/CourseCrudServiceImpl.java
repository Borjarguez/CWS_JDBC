package uo.ri.business.ServiceLayer.training.impl;

import uo.ri.business.ServiceLayer.training.CourseCrudService;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.BusinessException;

import java.util.List;
import java.util.Optional;

public class CourseCrudServiceImpl implements CourseCrudService {

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CourseDto> findCourseById(Long cId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
