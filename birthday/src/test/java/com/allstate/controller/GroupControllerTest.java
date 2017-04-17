package com.allstate.controller;

import com.allstate.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

//    @Autowired
//    GroupRepository groupRepository;

    @Autowired
    MockMvc mockMvc;

//    Groupo group;
//
//    @Before
//    public void setup() {
//        group = new Groupo();
//
//        List<User> users = new ArrayList<>();
//        User user1 = new User();
//        User user2 = new User();
//
//        users.add(user1);
//        users.add(user2);
//
//        group.setName("Groupo of Super Old People");
//        group.setUsers(users);
//    }
//
//    @After
//    public void teardown() {
//        groupRepository.deleteAll();
//    }

    @Test
    public void testCreateGroup() throws Exception {

        MockHttpServletRequestBuilder request = post("/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"kopkopkop\"}");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("kopkopkop")))
                .andExpect(jsonPath("$.id").exists());
    }

}
