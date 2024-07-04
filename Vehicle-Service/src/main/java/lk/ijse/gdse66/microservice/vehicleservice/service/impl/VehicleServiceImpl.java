package lk.ijse.gdse66.microservice.vehicleservice.service.impl;

import lk.ijse.gdse66.microservice.vehicleservice.dto.VehicleDTO;
import lk.ijse.gdse66.microservice.vehicleservice.persistance.entity.Vehicle;
import lk.ijse.gdse66.microservice.vehicleservice.persistance.repository.VehicleRepository;
import lk.ijse.gdse66.microservice.vehicleservice.service.VehicleService;
import lk.ijse.gdse66.microservice.vehicleservice.service.execption.DublicateRecordException;
import lk.ijse.gdse66.microservice.vehicleservice.service.execption.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    VehicleRepository vehicleRepository;
    ModelMapper modelMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return vehicleRepository.findAll().stream().map(
                vehicle -> modelMapper.map(vehicle,VehicleDTO.class)
        ).toList();
    }

    @Override
    public VehicleDTO getVehicleDetails(String number) {
        if(!vehicleRepository.existsByVehicleNumber(number)){
            throw new NotFoundException("Vehicle "+number+" Not Found!");
        }
        return modelMapper.map(vehicleRepository.findByVehicleNumber(number), VehicleDTO.class);
    }

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        if(vehicleRepository.existsByVehicleNumber(vehicleDTO.getVehicleNumber())){
            throw new DublicateRecordException("This Vehicle "+vehicleDTO.getVehicleNumber()+" already exicts...");
        }
        return modelMapper.map(vehicleRepository.save(modelMapper.map(
                vehicleDTO, Vehicle.class)), VehicleDTO.class
        );
    }

    @Override
    public void updateVehicle(String number, VehicleDTO vehicleDTO) {
        if(!vehicleRepository.existsByVehicleNumber(number)){
            throw new NotFoundException("Vehicle Number"+ number + "Not Found...");
        }
        vehicleDTO.setVehicleNumber(number);
        vehicleRepository.save(modelMapper.map(vehicleDTO,Vehicle.class));
    }

    @Override
    public void deleteVehicle(String number) {
        if(!vehicleRepository.existsByVehicleNumber(number)){
            throw  new NotFoundException("Vehicle Number"+ number + "Not Found...");
        }
        vehicleRepository.deleteByVehicleNumber(number);
    }
}
