package com.hgcode.bookstore.repository;

import com.hgcode.bookstore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);

    Optional<UserEntity> findOneByEmailAndPassword(String email, String password);
}
