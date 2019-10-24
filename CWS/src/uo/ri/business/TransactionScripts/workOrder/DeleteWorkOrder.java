package uo.ri.business.TransactionScripts.workOrder;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteWorkOrder {
    private Long workOrder_id;

    public DeleteWorkOrder(Long id) {
        this.workOrder_id = id;
    }

    public void execute() throws BusinessException {
        try (Connection c = Jdbc.getConnection()) {
            c.setAutoCommit(false);
            WorkOrderGateway mg = PersistenceFactory.getWorkOrderGateway();
            mg.setConnection(c);

            // Security check for a mechanic
            // Exists work order
            if (mg.findById(workOrder_id) == null) {
                c.rollback();
                throw new BusinessException("WorkOrder doesn't exist");
            }

            // Work order doesn't have interventions
            if (mg.findInterventions(workOrder_id)) {
                c.rollback();
                throw new BusinessException("WorkOrder has assigned interventions ");
            }
            //////////////////////////////////////

            mg.delete(workOrder_id);
            c.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Connection error");
        }
    }
}
