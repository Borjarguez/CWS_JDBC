package uo.ri.business.ServiceLayer.training.impl;

import uo.ri.business.ServiceLayer.training.CourseAttendanceService;
import uo.ri.business.TransactionScripts.courseAttendance.AddAttendance;
import uo.ri.business.TransactionScripts.courseAttendance.DeleteAttendance;
import uo.ri.business.TransactionScripts.courseAttendance.FindAllActiveCourses;
import uo.ri.business.TransactionScripts.courseAttendance.FindAttendanceByCourseId;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

import java.util.List;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    @Override
    public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
        AddAttendance ad = new AddAttendance(dto);
        return ad.execute();
    }

    @Override
    public void deleteAttendace(Long id) throws BusinessException {
        DeleteAttendance dl = new DeleteAttendance(id);
        dl.execute();
    }

    @Override
    public List<EnrollmentDto> findAttendanceByCourseId(Long course_id) throws BusinessException {
        FindAttendanceByCourseId fa = new FindAttendanceByCourseId(course_id);
        return fa.execute();
    }

    @Override
    public List<CourseDto> findAllActiveCourses() throws BusinessException {
        FindAllActiveCourses fa = new FindAllActiveCourses();
        return fa.execute();
    }

    @Override
    public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
        // TODO Auto-generated method stub
        return null;
    }


}
