package com.hgcode.bookstore.repository;

import com.hgcode.bookstore.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class IUserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    UserEntity userEntityOne;
    UserEntity userEntityTwo;

    @BeforeEach
    void setUp() {
        userEntityOne = new UserEntity(1L,"giang","giang@gmail.com","123456");
        userEntityTwo = new UserEntity(2L,"giang1","giang1@gmail.com","123456");
        userRepository.save(userEntityOne);
        userRepository.save(userEntityTwo);
    }

    @AfterEach
    void tearDown() {
        userEntityOne = null;
        userEntityTwo = null;
        userRepository.deleteAll();
    }

    // Test case Success
    @Test
    void testFindByEmail_Found() {
        UserEntity userEntity = userRepository.findByEmail("giang@gmail.com");
        assertThat(userEntity.getUsername()).isEqualTo(userEntityOne.getUsername());
    }
    // Test case Failure
    @Test
    void testFindByEmail_NotFound() {
        UserEntity userEntity = userRepository.findByEmail("giang2@gmail.com");
        assertThat(userEntity).isEqualTo(null);
    }

    @Test
    void testFindOneByEmailAndPassword() {
        Optional<UserEntity> userEntity = userRepository.findOneByEmailAndPassword("giang1@gmail.com","123456");
        assertThat(userEntity.get().getUsername()).isEqualTo(userEntityTwo.getUsername());
    }
}