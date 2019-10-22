package uo.ri.business.ServiceLayer.training.impl;

import uo.ri.business.ServiceLayer.training.CertificateService;
import uo.ri.business.TransactionScripts.certificate.GenerateCertificate;
import uo.ri.common.BusinessException;

public class CertificateServiceImpl implements CertificateService{

	@Override
	public int generateCertificates() throws BusinessException {
		GenerateCertificate gc = new GenerateCertificate();
		return gc.execute();
	}

}
