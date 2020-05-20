package uo.ri.business.TransactionScripts.workOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.vehicle.VehicleGateway;
import uo.ri.persistence.workOrder.WorkOrderGateway;

public class FindWorkOrdersByPlateNumber {
	private String plate;

	public FindWorkOrdersByPlateNumber(String plate) {
		this.plate = plate;
	}

	public List<WorkOrderDto> execute() {
		try (Connection c = Jdbc.getConnection()) {
			List<WorkOrderDto> workorders = new ArrayList<WorkOrderDto>();
			WorkOrderGateway mg = PersistenceFactory.getWorkOrderGateway();
			VehicleGateway vg = PersistenceFactory.getVehicleGateway();
			vg.setConnection(c);
			mg.setConnection(c);

			Optional<VehicleDto> vehiculo = vg.findByPlate(plate);
			
			if (vehiculo.get() == null)
				return workorders;
			
			workorders = mg.findWorkOrdersByVehicleId(vehiculo.get().id);
			return workorders;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}
}
