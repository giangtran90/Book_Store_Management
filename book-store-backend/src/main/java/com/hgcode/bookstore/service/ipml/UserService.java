package com.hgcode.bookstore.service.ipml;

import com.hgcode.bookstore.entity.UserEntity;
import com.hgcode.bookstore.model.User;
import com.hgcode.bookstore.repository.IUserRepository;
import com.hgcode.bookstore.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                this.passwordEncoder.encode(user.getPassword())
        );
        userRepository.save(userEntity);
        return user.getUsername();
    }
}
