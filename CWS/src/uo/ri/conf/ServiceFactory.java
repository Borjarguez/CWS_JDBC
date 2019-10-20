package uo.ri.conf;

import uo.ri.business.ServiceLayer.invoice.InvoiceService;
import uo.ri.business.ServiceLayer.invoice.impl.InvoiceServiceImpl;
import uo.ri.business.ServiceLayer.mechanic.MechanicCrudService;
import uo.ri.business.ServiceLayer.mechanic.impl.MechanicCrudServiceImpl;

public class ServiceFactory {
	
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}
}
