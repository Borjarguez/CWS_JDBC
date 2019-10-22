package uo.ri.business.ServiceLayer.training.impl;

import java.util.List;

import uo.ri.business.ServiceLayer.training.CourseAttendanceService;
import uo.ri.business.TransactionScripts.courseAttendance.FindAttendanceByCourseId;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAttendace(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long course_id) throws BusinessException {
		FindAttendanceByCourseId fa = new FindAttendanceByCourseId(course_id);
		return fa.execute();
	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


}
