package com.imt.intes.service;

import com.imt.intes.model.UserEntity;
import com.imt.intes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(username);
        if (user.isPresent()) {
            return new User(
                    user.get().getLogin(),
                    user.get().getPassword(),
                    user.get().isEnable(),
                    true,
                    true,
                    true,
                    user.get().getRoles());
        }
        throw new UsernameNotFoundException(username);
    }

}
