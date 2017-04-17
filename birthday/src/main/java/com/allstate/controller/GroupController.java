package com.allstate.controller;

import com.allstate.domain.Groupo;
import com.allstate.repository.GroupRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository repository;

    public GroupController(GroupRepository repository){
        this.repository = repository;
    }

    @PostMapping("")
    public Groupo createGroup(@RequestBody Groupo group) {
        this.repository.save(group);
        return group;
    }


}
