package uo.ri.business.ServiceLayer.training.impl;

import uo.ri.business.ServiceLayer.training.CourseReportService;
import uo.ri.business.TransactionScripts.courseReport.FindTrainingByMechanicID;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.common.BusinessException;

import java.util.List;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws BusinessException {
		FindTrainingByMechanicID fr = new FindTrainingByMechanicID(id);
		return fr.execute();
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		return null;
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		return null;
	}

}
