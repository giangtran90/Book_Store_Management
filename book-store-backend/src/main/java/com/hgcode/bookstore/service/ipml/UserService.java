package com.hgcode.bookstore.service.ipml;

import com.hgcode.bookstore.entity.UserEntity;
import com.hgcode.bookstore.model.Login;
import com.hgcode.bookstore.model.User;
import com.hgcode.bookstore.repository.IUserRepository;
import com.hgcode.bookstore.response.LoginResponse;
import com.hgcode.bookstore.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public LoginResponse loginUser(Login login) {
        UserEntity userEntity = userRepository.findByEmail(login.getEmail());
        if (userEntity != null){
            String password = login.getPassword();
            String encodedPassword = userEntity.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password,encodedPassword);
            if (isPwdRight){
                Optional<UserEntity> user = userRepository.findOneByEmailAndPassword(login.getEmail(),encodedPassword);
                if (user.isPresent()){
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Email or password not right", false);
            }
        } else {
            return new LoginResponse("Email or password not right", false);
        }
    }
}
