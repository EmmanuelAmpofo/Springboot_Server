package server.Spring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.Spring.model.User;
import server.Spring.service.UserService;

import java.util.Collection;


@RestController
@RequestMapping("/api")

@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registeruser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userAdd = userService.createUser(user);
        return new ResponseEntity<>(userAdd, HttpStatus.CREATED);
    }
//
    @GetMapping("/users")
    public ResponseEntity<Collection<User>> getAllUsers(){
        Collection<User> users = userService.listAllUsers(10);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable("id") Long id){
        User singleUser = userService.getUser(id);
        return new ResponseEntity<>(singleUser, HttpStatus.OK);
    }

    @PutMapping("/edituser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User userUpdate = userService.updateUser(user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
