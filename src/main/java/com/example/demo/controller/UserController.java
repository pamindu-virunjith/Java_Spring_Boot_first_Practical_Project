package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin

public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/getUsers")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUserById(@PathVariable Integer userId){
        return userService.getOneUser(userId);
    }

    @PostMapping("/createuser")
    public  UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

//    this is working as Upsert Method. which means this can update  existing information or if not it create new info object.
//    So, this can perform both update and insert operations.
    @PutMapping("/updateuser")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

//    first deleting method - delete user by using user id passed as request body
    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteUser(userDTO);
    }

//    delete user by using path parameter
    @DeleteMapping("/deleteuser/{userId}")
    public String deleteUser(@PathVariable int userId){
        return userService.deleteUserWithParthVariable(userId);
    }
}
