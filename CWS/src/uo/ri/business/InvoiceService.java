package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.dto.BreakdownDto;
import uo.ri.dto.InvoiceDto;
import uo.ri.dto.PaymentMeanDto;

public interface InvoiceService {

	InvoiceDto createInvoiceFor(List<Long> idsAveria) throws BusinessException;
	InvoiceDto findInvoice(Long numeroFactura) throws BusinessException;
	List<PaymentMeanDto> findPayMethodsForInvoice(Long idFactura) throws BusinessException;
	void settleInvoice(Long idFactura, Map<Long, Double> cargos) throws BusinessException;

	List<BreakdownDto> findRepairsByClient(String dni) throws BusinessException;
	
}
