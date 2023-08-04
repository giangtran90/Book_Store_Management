package com.hgcode.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hgcode.bookstore.entity.UserEntity;
import com.hgcode.bookstore.model.Book;
import com.hgcode.bookstore.model.User;
import com.hgcode.bookstore.service.IBookService;
import com.hgcode.bookstore.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class UserControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    UserEntity userEntity;
    User user;

    @BeforeEach
    void setUp() {
        user = new User(1L,"giang","giang@gmail.com","123456");
        userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateUser() throws Exception {
        String requestJson = writeValueAsString(user);
        when(userService.createUser(user)).thenReturn(user.getUsername());
        this.mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void loginUser() {
    }

    private String writeValueAsString(User user) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);

        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(user);
    }
}