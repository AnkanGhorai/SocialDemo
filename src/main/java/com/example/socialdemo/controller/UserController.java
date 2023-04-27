package com.example.socialdemo.controller;

import com.example.socialdemo.exception.UserNotFoundException;
import com.example.socialdemo.service.UserService;
import com.example.socialdemo.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class UserController {


    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    //http://localhost:8080/users
    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
    // http://localhost:8080/usersFilter?id=1
    @GetMapping("/usersFilter")
    public User getUserById(@RequestParam int id) throws UserNotFoundException{
        Supplier<UserNotFoundException> userNotFoundExceptionSupplier = () -> new UserNotFoundException("Id not found: " + id);
        return userService.findById(id).orElseThrow(userNotFoundExceptionSupplier);
    }
    // http://localhost:8080/users/{1}
    @GetMapping("/users/{id}")
    public User getUserByIdV2(@PathVariable int id) throws UserNotFoundException{
        Supplier<UserNotFoundException> userNotFoundExceptionSupplier = () -> new UserNotFoundException("Id not found: " + id);
        return userService.findById(id).orElseThrow(userNotFoundExceptionSupplier);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserBy(@PathVariable int id){
        userService.deleteById(id);
    }


    @PostMapping("/usersV1")
    public User saveUserV1(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping("/users")
    public ResponseEntity saveUserV2(@Valid @RequestBody User user){
        User tempUser = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tempUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
