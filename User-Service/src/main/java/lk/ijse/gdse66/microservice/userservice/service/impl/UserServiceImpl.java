package lk.ijse.gdse66.microservice.userservice.service.impl;

import lk.ijse.gdse66.microservice.userservice.dto.UserDTO;
import lk.ijse.gdse66.microservice.userservice.persistance.entity.User;
import lk.ijse.gdse66.microservice.userservice.persistance.repository.UserRepository;
import lk.ijse.gdse66.microservice.userservice.service.UserService;
import lk.ijse.gdse66.microservice.userservice.service.execptiom.DublicateRecordException;
import lk.ijse.gdse66.microservice.userservice.service.execptiom.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        System.out.println("Trying to get all users...");
        return userRepository.findAll().stream().map(
                users -> modelMapper.map(users,UserDTO.class)
        ).toList();
    }

    @Override
    public UserDTO getUserDetails(String nic) {
        if(!userRepository.existsByUserNic(nic)){
            throw new NotFoundException("User "+nic+" Not Found!");
        }
        return modelMapper.map(userRepository.findByUserNic(nic), UserDTO.class);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if(userRepository.existsByUserNic(userDTO.getUserNic())){
            throw new DublicateRecordException("This User "+userDTO.getUserNic()+" already exicts...");
        }
        return modelMapper.map(userRepository.save(modelMapper.map(
                userDTO, User.class)), UserDTO.class
        );
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
        if(!userRepository.existsByUserNic(id)){
            throw new NotFoundException("User NIC"+ id + "Not Found...");
        }
        userDTO.setUserNic(id);
        userRepository.save(modelMapper.map(userDTO,User.class));
    }

    @Override
    public void deleteUser(String id) {
        if(!userRepository.existsByUserNic(id)){
            throw  new NotFoundException("User NIC"+ id + "Not Found...");
        }
        userRepository.deleteByUserNic(id);
    }
}
