package com.hgcode.bookstore.repository;

import com.hgcode.bookstore.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class IUserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity(1L,"giang","giang@gmail.com","123456");
        userRepository.save(userEntity);
    }

    @AfterEach
    void tearDown() {
        userEntity = null;
        userRepository.deleteAll();
    }

    // Test case Success
    @Test
    void testFindByEmail_Found() {
        UserEntity userEntity1 = userRepository.findByEmail("giang@gmail.com");
        assertThat(userEntity1.getId()).isEqualTo(userEntity.getId());
    }
    // Test case Failure
    @Test
    void testFindByEmail_NotFound() {
        UserEntity userEntity1 = userRepository.findByEmail("giang1@gmail.com");
        assertThat(userEntity1).isEqualTo(null);
    }
}