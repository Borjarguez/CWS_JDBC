package uo.ri.business.ServiceLayer.workOrder.impl;

import uo.ri.business.ServiceLayer.workOrder.WorkOrderService;
import uo.ri.business.TransactionScripts.workOrder.AddWorkOrder;
import uo.ri.business.TransactionScripts.workOrder.DeleteWorkOrder;
import uo.ri.business.TransactionScripts.workOrder.FindWorkOrderByID;
import uo.ri.business.TransactionScripts.workOrder.UpdateWorkOrder;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;

import java.util.List;
import java.util.Optional;

public class WorkOrderServiceImpl implements WorkOrderService {
    @Override
    public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
        AddWorkOrder ad = new AddWorkOrder(dto);
        return ad.execute();
    }

    @Override
    public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
        UpdateWorkOrder up = new UpdateWorkOrder(dto);
        up.execute();
    }

    @Override
    public void deleteWorkOrder(Long id) throws BusinessException {
        DeleteWorkOrder dl = new DeleteWorkOrder(id);
        dl.execute();
    }

    @Override
    public Optional<WorkOrderDto> findWorkOrderById(Long woId) throws BusinessException {
        FindWorkOrderByID fid = new FindWorkOrderByID(woId);
        return fid.execute();
    }

    @Override
    public List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException {
        return null;
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByVehicleId(Long id) throws BusinessException {
        return null;
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
        return null;
    }

    @Override
    public List<CertificateDto> findCertificatesByVehicleTypeId(Long id) throws BusinessException {
        return null;
    }

    @Override
    public void assignWorkOrderToMechanic(Long woId, Long mechanicId) throws BusinessException {

    }
}
