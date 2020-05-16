package uo.ri.business.TransactionScripts.workOrder;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.workOrder.WorkOrderGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class AddWorkOrder {
    private WorkOrderDto dto;

    public AddWorkOrder(WorkOrderDto dto) {
        this.dto = dto;
    }

    public WorkOrderDto execute() {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            WorkOrderGateway wg = PersistenceFactory.getWorkOrderGateway();
            wg.setConnection(c);
            wg.add(dto);
            c.commit();
            dto.id =  wg.findLastWorkOrder().id;
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
