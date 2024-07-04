package lk.ijse.gdse66.microservice.vehicleservice.service;

import lk.ijse.gdse66.microservice.vehicleservice.dto.VehicleDTO;

import java.util.List;



public interface VehicleService {
    List<VehicleDTO> getAllVehicle();
    VehicleDTO getVehicleDetails(String number);
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(String number, VehicleDTO vehicleDTO);
    void deleteVehicle(String number);
}
