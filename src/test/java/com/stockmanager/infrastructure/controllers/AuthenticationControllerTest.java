package com.stockmanager.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmanager.config.security.dto.JwtResponse;
import com.stockmanager.config.security.dto.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void shouldLogInAndCreateContent() throws Exception {
        //given
        LoginRequestDto loginRequestDto = new LoginRequestDto("admin", "adminpass");
        String loginContent = objectMapper.writeValueAsString(loginRequestDto);

        //when
        MvcResult login = mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginContent))
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn();


        JwtResponse token = objectMapper.readValue(login.getResponse().getContentAsString(), JwtResponse.class);
        System.out.println(token);
        String authHeader = "Bearer " + token.token();

        mockMvc.perform(get("/secured")
                .header("Authorization", authHeader))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string("secured"));

        mockMvc.perform(get("/secured"))
                .andDo(print())
                .andExpect(status().is(403));
    }
}