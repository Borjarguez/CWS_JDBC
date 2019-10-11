package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.InvoiceService;
import uo.ri.business.impl.casher.WorkOrderBilling;
import uo.ri.common.BusinessException;
import uo.ri.dto.BreakdownDto;
import uo.ri.dto.InvoiceDto;
import uo.ri.dto.PaymentMeanDto;

public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public InvoiceDto createInvoiceFor(List<Long> idsAveria) throws BusinessException {
		WorkOrderBilling bl = new WorkOrderBilling(idsAveria);
		return bl.execute();
	}

	@Override
	public InvoiceDto findInvoice(Long numeroFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPayMethodsForInvoice(Long idFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(Long idFactura, Map<Long, Double> cargos) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BreakdownDto> findRepairsByClient(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
