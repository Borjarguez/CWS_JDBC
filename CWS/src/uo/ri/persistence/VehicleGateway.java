package uo.ri.persistence;

import uo.ri.business.dto.VehicleDto;

import java.sql.Connection;
import java.util.Optional;

public interface VehicleGateway {
    void setConnection(Connection con);

    Optional<VehicleDto> findByPlate(String plate);
}
