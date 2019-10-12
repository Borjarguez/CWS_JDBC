package uo.ri.conf;

import uo.ri.business.ServiceLayer.InvoiceService;
import uo.ri.business.ServiceLayer.MechanicCrudService;
import uo.ri.business.ServiceLayer.impl.InvoiceServiceImpl;
import uo.ri.business.ServiceLayer.impl.MechanicCrudServiceImpl;

public class ServiceFactory {
	
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}
}
