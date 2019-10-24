package uo.ri.business.TransactionScripts.workOrder;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class FindWorkOrderByID {
    private Long workOrderId;

    public FindWorkOrderByID(Long workOrderId){
        this.workOrderId = workOrderId;
    }

    public Optional<WorkOrderDto> execute(){
        try(Connection c = Jdbc.getConnection()){
            c.setAutoCommit(false);
            WorkOrderGateway wg = PersistenceFactory.getWorkOrderGateway();
            wg.setConnection(c);
            return Optional.ofNullable(wg.findById(workOrderId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
