package uo.ri.business.ServiceLayer.training.impl;

import java.util.List;

import uo.ri.business.ServiceLayer.training.CourseReportService;
import uo.ri.business.TransactionScripts.courseReport.FindCertificatedByVehicleType;
import uo.ri.business.TransactionScripts.courseReport.FindTrainingByMechanicID;
import uo.ri.business.TransactionScripts.courseReport.FindTrainingByVehicleTypeAndMechanic;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.common.BusinessException;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws BusinessException {
		FindTrainingByMechanicID fr = new FindTrainingByMechanicID(id);
		return fr.execute();
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		FindTrainingByVehicleTypeAndMechanic fin = new FindTrainingByVehicleTypeAndMechanic();
		return fin.execute();
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		FindCertificatedByVehicleType ce = new FindCertificatedByVehicleType();
		return ce.execute();
	}

}
