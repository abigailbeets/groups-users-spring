package com.allstate.controller;


import com.allstate.domain.User;
import com.allstate.repository.UserRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    User user;
    User user2;

    @Before
    public void setup() {
        userRepository.deleteAll();

        user = new User();
        user.setFirst("Abigail");
        user.setLast("Beets");
        user.setBirthday("01-01-1980");

        user2 = new User();
        user2.setFirst("Zach");
        user2.setLast("Quinn");
        user2.setBirthday("01-01-1930");
    }

    @After
    public void teardown() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() throws Exception {
        userRepository.deleteAll();

        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"first\": \"Abby\", \"last\": \"Beets\", \"birthday\": \"01-01-2000\"}");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first", equalTo("Abby")))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.last", equalTo("Beets")))
                .andExpect(jsonPath("$.birthday", equalTo("01-01-2000")))
                .andExpect(jsonPath("$.nickname", equalTo("Dope Abby")));

        assertEquals("repo should have size 1", 1, userRepository.findAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        userRepository.save(user);
        userRepository.save(user2);

        MockHttpServletRequestBuilder request = get("/users");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].first", equalTo("Abigail")))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[1].first", equalTo("Zach")))
                .andExpect(jsonPath("$[1].id", equalTo(2)));
    }

    @Test
    public void testGetUserById() throws Exception {
        userRepository.save(user);
        userRepository.save(user2);

        MockHttpServletRequestBuilder request = get("/users/"+user2.getId());

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first", equalTo("Zach")))
                .andExpect(jsonPath("$.id", equalTo(user2.getId().intValue())));
    }

    @Test
    public void testDeleteUser() throws Exception {
        userRepository.save(user);
        userRepository.save(user2);

        assertEquals("repo should have size 2", 2, userRepository.findAll().spliterator().getExactSizeIfKnown());


        MockHttpServletRequestBuilder request = delete("/users/" + user.getId());

        this.mockMvc.perform(request)
                .andExpect(status().isOk());

        assertEquals("repo should have size 1", 1, userRepository.findAll().spliterator().getExactSizeIfKnown());
    }
}

