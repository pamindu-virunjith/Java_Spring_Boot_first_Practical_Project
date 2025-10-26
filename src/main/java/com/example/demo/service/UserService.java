package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //define this class as a Service
@Transactional  //manipulate data transactions

public class UserService {
    @Autowired //Inject dependencies
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        List<User>userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public  UserDTO getOneUser(Integer userId){
        User user = userRepo.getOneUserById(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    public  UserDTO createUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }
//First deleting method - delete user with payload(user id passed in the req body)
    public String deleteUser(UserDTO userDTO){
        userRepo.delete(modelMapper.map(userDTO, User.class));
        return "user deleted";
    }

//    Second delete method - delete user using parth variable(user id passed in the url)
    public String deleteUserWithParthVariable(Integer userId){
        userRepo.deleteById(userId);
        return "user deleted";
    }
}
