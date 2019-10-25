package uo.ri.persistence;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;

import java.sql.Connection;
import java.util.List;

public interface InvoiceGateway {
	
	/**
	 * Method which sets the connection
	 * @param con
	 */
	void setConnection(Connection con);
	
	/**
     * Method which adds the element to the database
     * @param e
     */
	public long add(InvoiceDto in);
	
	/**
	 * Method which test repairs
	 * @param workOrderIDS
	 * @throws BusinessException
	 */
	public void testRepairs(List<Long> workOrderIDS) throws BusinessException;
	
	/**
	 * Method which generates the invoice number
	 * @return
	 */
	public Long generateInvoiceNumber();
	
	/**
	 * Method which returns the generated key
	 * @param numberInvoice
	 * @return
	 */
	public long getGeneratedKey(long numberInvoice);
	
	/**
	 * Method which updates the work order total
	 * @param workOrderID
	 * @param total
	 */
	public void updateWorkorderTotal(Long workOrderID, double total);
	
	/**
	 * Method which checks
	 * @param workOrderID
	 * @return
	 * @throws BusinessException
	 */
	public double checkTotalParts(Long workOrderID) throws BusinessException;
	
	/**
	 * Method which updates the status
	 * @param breakdownIds
	 * @param status
	 */
	public void updateWorkOrderStatus(List<Long> breakdownIds, String status);
	
	/**
	 * Method which links the work order with the invoice
	 * @param invoiceId
	 * @param workOrderIDS
	 */
	public void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS);
}
