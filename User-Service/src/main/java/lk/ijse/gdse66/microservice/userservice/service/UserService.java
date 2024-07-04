package lk.ijse.gdse66.microservice.userservice.service;

import lk.ijse.gdse66.microservice.userservice.dto.UserDTO;

import java.util.List;


public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserDetails(String id);
    UserDTO saveUser(UserDTO userDTO);
    void updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);
}
