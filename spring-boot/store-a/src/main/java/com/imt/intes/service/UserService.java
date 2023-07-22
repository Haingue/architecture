package com.imt.intes.service;

import com.imt.intes.model.UserEntity;
import com.imt.intes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll () {
        return this.userRepository.findAllByOrderByLogin();
    }

    public Optional<UserEntity> findOneByLogin (String login) {
        return this.userRepository.findById(login);
    }

    public UserEntity createNewUser (UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setEnable(true);
        return userRepository.save(userEntity);
    }

    public void updateUserPassword (String login, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<UserEntity> existingUser = this.userRepository.findById(login);
        if (!existingUser.isPresent()) {
            throw new EntityNotFoundException(String.format("No user entity found with login: %s", login));
        }
        UserEntity userEntity = existingUser.get();
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
    }

    public void updateUser (UserEntity userEntity) {
        Optional<UserEntity> existingUser = this.userRepository.findById(userEntity.getLogin());
        if (!existingUser.isPresent()) {
            throw new EntityNotFoundException(String.format("No user entity found with login: %s", userEntity.getLogin()));
        }
        UserEntity existingUserEntity = existingUser.get();
        existingUserEntity.setAddress(userEntity.getAddress());
        existingUserEntity.setRoles(userEntity.getRoles());
        existingUserEntity.setEnable(userEntity.isEnable());
        userRepository.save(existingUserEntity);
    }

    public void deleteUser (String login) {
        userRepository.deleteById(login);
    }
}
