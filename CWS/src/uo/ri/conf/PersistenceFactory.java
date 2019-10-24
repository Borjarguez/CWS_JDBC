package uo.ri.conf;

import uo.ri.persistence.*;
import uo.ri.persistence.impl.*;

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
}
