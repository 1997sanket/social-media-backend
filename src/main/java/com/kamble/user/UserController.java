package com.kamble.user;

import com.kamble.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired private UserDaoService userDaoService;

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userDaoService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> optUser = userDaoService.getUserById(id);
        if(optUser.isPresent()) {
            return optUser.get();
        }
        else throw new UserNotFoundException("No user found with id: " + id);
    }

    @PostMapping("users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
       User savedUser = userDaoService.saveUser(user);

       //Our POST method should also return the location of the resource created
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userDaoService.deleteUserById(id);
    }
}
