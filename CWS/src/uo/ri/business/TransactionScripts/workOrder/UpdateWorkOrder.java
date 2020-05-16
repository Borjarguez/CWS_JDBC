package uo.ri.business.TransactionScripts.workOrder;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.workOrder.WorkOrderGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateWorkOrder {
    private WorkOrderDto dto;

    public UpdateWorkOrder(WorkOrderDto dto) {
        this.dto = dto;
    }

    public void execute() throws BusinessException {
        try(Connection c = Jdbc.getConnection()){
            c.setAutoCommit(false);
            WorkOrderGateway wg = PersistenceFactory.getWorkOrderGateway();
            wg.setConnection(c);

            // Security checks /////////////////////////////////////////
            if(wg.findById(dto.id) == null){
                c.rollback();
                throw new BusinessException("WorkOrder doesn't exist");
            }
            ////////////////////////////////////////////////////////////

            wg.update(dto);
            c.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
