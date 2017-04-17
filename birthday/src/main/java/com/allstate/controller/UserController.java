package com.allstate.controller;

import com.allstate.domain.User;
import com.allstate.repository.UserRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        this.repository.save(user);
        return user;
    }

    @GetMapping("")
    public Iterable<User> listUsers() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable Long id) {
        return this.repository.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSingleUser(@PathVariable Long id) {
        this.repository.delete(id);
    }
}
