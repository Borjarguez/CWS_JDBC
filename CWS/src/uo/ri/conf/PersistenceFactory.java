package uo.ri.conf;

import uo.ri.persistence.certificate.CertificateGateway;
import uo.ri.persistence.certificate.impl.CertificateGatewayImpl;
import uo.ri.persistence.course.CourseGateway;
import uo.ri.persistence.course.impl.CourseGatewayImpl;
import uo.ri.persistence.courseAttendance.CourseAttendanceGateway;
import uo.ri.persistence.courseAttendance.impl.CourseAttendanceGatewayImpl;
import uo.ri.persistence.dedication.DedicationGateway;
import uo.ri.persistence.dedication.impl.DedicationGatewayImpl;
import uo.ri.persistence.invoice.InvoiceGateway;
import uo.ri.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.persistence.mechanic.MechanicGateway;
import uo.ri.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.persistence.vehicle.VehicleGateway;
import uo.ri.persistence.vehicle.impl.VehicleGatewayImpl;
import uo.ri.persistence.vehicleType.VehicleTypeGateway;
import uo.ri.persistence.vehicleType.impl.VehicleTypeGatewayImpl;
import uo.ri.persistence.workOrder.WorkOrderGateway;
import uo.ri.persistence.workOrder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {
	
	public static MechanicGateway getMechanicGateway() {
		return new MechanicGatewayImpl();
	}
	
	public static InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}

	public static CertificateGateway getCertificateGateway() {
		return new CertificateGatewayImpl();
	}

	public static VehicleTypeGateway getVehicleTypeGateway() {
		return new VehicleTypeGatewayImpl();
	}

	public static CourseGateway getCourseGateway() {
		return new CourseGatewayImpl();
	}

	public static CourseAttendanceGateway getCourseAttendanceGateway() {
		return new CourseAttendanceGatewayImpl();
	}

	public static WorkOrderGateway getWorkOrderGateway() {
		return new WorkOrderGatewayImpl();
	}

    public static VehicleGateway getVehicleGateway() { return new VehicleGatewayImpl(); }

	public static DedicationGateway getDedicationsGateway() { return new DedicationGatewayImpl(); }
}
