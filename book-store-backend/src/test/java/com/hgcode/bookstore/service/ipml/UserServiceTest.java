package com.hgcode.bookstore.service.ipml;

import com.hgcode.bookstore.entity.UserEntity;
import com.hgcode.bookstore.model.User;
import com.hgcode.bookstore.repository.IUserRepository;
import com.hgcode.bookstore.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private IUserService userService;

    AutoCloseable autoCloseable;

    UserEntity userEntity;

    User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository,passwordEncoder);
        user = new User(1L,"giang","giang@gmail.com", "123456");
        userEntity = new UserEntity(user.getId(),user.getUsername(),user.getEmail(),this.passwordEncoder.encode(user.getPassword()));
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateUser() {
        System.out.println(userEntity);
        mock(UserEntity.class);
        mock(IUserRepository.class);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        assertThat(userService.createUser(user)).isEqualTo("giang");
    }

    @Test
    void loginUser() {
    }
}