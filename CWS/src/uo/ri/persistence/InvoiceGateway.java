package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;

public interface InvoiceGateway {
	
	void setConnection(Connection con);
	
	public long add(InvoiceDto in);
	
	public void testRepairs(List<Long> workOrderIDS) throws BusinessException;
	
	public Long generateInvoiceNumber();
	
	public long getGeneratedKey(long numberInvoice);
	
	public void updateWorkorderTotal(Long workOrderID, double total);
	
	public double checkTotalParts(Long workOrderID) throws BusinessException;
	
	public double checkTotalLabor(Long workOrderID) throws BusinessException;
	
	public void updateWorkOrderStatus(List<Long> breakdownIds, String status);
	
	public void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS);
}
