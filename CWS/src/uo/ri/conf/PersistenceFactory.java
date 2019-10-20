package uo.ri.conf;

import uo.ri.persistence.CertificateGateway;
import uo.ri.persistence.InvoiceGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.VehicleTypeGateway;
import uo.ri.persistence.impl.CertificateGatewayImpl;
import uo.ri.persistence.impl.InvoiceGatewayImpl;
import uo.ri.persistence.impl.MechanicGatewayImpl;
import uo.ri.persistence.impl.VehicleTypeGatewayImpl;

public class PersistenceFactory {
	
	public static MechanicGateway getMechanicGateway() {
		return new MechanicGatewayImpl();
	}
	
	public static InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}

	public static CertificateGateway getCourseGateway() {
		return new CertificateGatewayImpl();
	}

	public static VehicleTypeGateway getVehicleTypeGateway() {
		return new VehicleTypeGatewayImpl();
	}
}
