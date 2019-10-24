package uo.ri.persistence;

import uo.ri.business.dto.WorkOrderDto;

import java.sql.Connection;

public interface WorkOrderGateway {
    void setConnection(Connection con);

    void add(WorkOrderDto m);

    void delete(Long workOrder_id);

    void update(WorkOrderDto m);

    WorkOrderDto findLastWorkOrder();

    WorkOrderDto findById(Long workOrder_id);

    boolean findInterventions(Long workOrder_id);
}
