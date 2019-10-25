package uo.ri.business.ServiceLayer.mechanic.impl;

import uo.ri.business.ServiceLayer.mechanic.MechanicCrudService;
import uo.ri.business.TransactionScripts.mechanic.*;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

import java.util.List;

public class MechanicCrudServiceImpl implements MechanicCrudService {

	@Override
	public void addMechanic(MechanicDto mechanic) throws BusinessException {
		AddMechanic ad = new AddMechanic(mechanic);
		ad.execute();
	}

	@Override
	public void deleteMechanic(Long mechanic_id) throws BusinessException {
		DeleteMechanic dl = new DeleteMechanic(mechanic_id);
		dl.execute();
	}

	@Override
	public void updateMechanic(MechanicDto mechanic) throws BusinessException {
		UpdateMechanic up = new UpdateMechanic(mechanic);
		up.execute();
	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		FindMechanicByID fi = new FindMechanicByID(id);
		return fi.execute();
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		FindAllMechanics fm = new FindAllMechanics();
		return fm.execute();
	}

}
