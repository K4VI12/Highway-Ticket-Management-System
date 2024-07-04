package lk.ijse.gdse66.microservice.vehicleservice.persistance.repository;

import lk.ijse.gdse66.microservice.vehicleservice.persistance.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VehicleRepository extends JpaRepository<Vehicle,String> {
    Vehicle findByVehicleNumber(String number);
    Boolean existsByVehicleNumber(String number);
    void deleteByVehicleNumber(String number);
}
