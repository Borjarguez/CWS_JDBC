package uo.ri.conf;

import uo.ri.business.ServiceLayer.invoice.InvoiceService;
import uo.ri.business.ServiceLayer.invoice.impl.InvoiceServiceImpl;
import uo.ri.business.ServiceLayer.mechanic.MechanicCrudService;
import uo.ri.business.ServiceLayer.mechanic.impl.MechanicCrudServiceImpl;
import uo.ri.business.ServiceLayer.training.CertificateService;
import uo.ri.business.ServiceLayer.training.CourseAttendanceService;
import uo.ri.business.ServiceLayer.training.CourseCrudService;
import uo.ri.business.ServiceLayer.training.CourseReportService;
import uo.ri.business.ServiceLayer.training.impl.CertificateServiceImpl;
import uo.ri.business.ServiceLayer.training.impl.CourseAttendanceServiceImpl;
import uo.ri.business.ServiceLayer.training.impl.CourseCrudServiceImpl;
import uo.ri.business.ServiceLayer.training.impl.CourseReportServiceImpl;
import uo.ri.business.ServiceLayer.vehicle.VehicleCrudService;
import uo.ri.business.ServiceLayer.vehicle.impl.VehicleCrudServiceImpl;
import uo.ri.business.ServiceLayer.workOrder.WorkOrderService;
import uo.ri.business.ServiceLayer.workOrder.impl.WorkOrderServiceImpl;

public class ServiceFactory {
	
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}
	
	public static CourseAttendanceService getCourseAttendanceService() {
		return new CourseAttendanceServiceImpl();
	}

	public static CourseCrudService getCourseCrudService() {
		return new CourseCrudServiceImpl();
	}

	public static CertificateService getCertificateService() {
		return new CertificateServiceImpl();
	}

	public static CourseReportService getCourseReportService() {
		return new CourseReportServiceImpl();
	}

    public static WorkOrderService getWorkOrderService() { return new WorkOrderServiceImpl(); }

	public static VehicleCrudService getVehicleCrudService() { return new VehicleCrudServiceImpl();	}
}
