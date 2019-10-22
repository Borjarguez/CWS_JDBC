package uo.ri.business.ServiceLayer.training.impl;

import java.util.List;

import uo.ri.business.ServiceLayer.training.CourseReportService;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.common.BusinessException;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
