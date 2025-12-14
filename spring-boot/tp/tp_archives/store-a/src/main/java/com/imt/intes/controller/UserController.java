package com.imt.intes.controller;

import com.imt.intes.configuration.security.Role;
import com.imt.intes.dto.UserDto;
import com.imt.intes.mapper.UserMapper;
import com.imt.intes.model.UserEntity;
import com.imt.intes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUser (@PathParam("login") String login, Authentication authentication) {
        logger.info("Load user");
        Function<UserEntity, UserDto> mapper;
        if (authentication != null && authentication.getAuthorities().contains(Role.ROLE_MANAGER)) {
            mapper = (UserEntity entity) -> UserMapper.entityToDto(entity);
        } else {
            mapper = (UserEntity entity) -> UserMapper.entityToDtoAnonymized(entity);
        }
        if(login == null) {
            List<UserEntity> userEntities = userService.findAll();
            return ResponseEntity.ok(
                    userEntities.stream().map(mapper).collect(Collectors.toList()));
        } else {
            Optional<UserEntity> userEntity = userService.findOneByLogin(login);
            if (userEntity.isPresent()) return ResponseEntity.ok(userEntity.map(mapper).get());
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    private ResponseEntity postUser (@RequestBody UserDto userDto) {
        if (userDto != null && userDto.isValidLogin()) {
            userService.createNewUser(UserMapper.dtoToEntity(userDto));
        }
        return ResponseEntity.badRequest()
                .header("Error", "Invalid login")
                .build();
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    private ResponseEntity putUser (@RequestBody UserDto userDto) {
        if (userDto != null && userDto.isValidLogin()) {
            userService.updateUser(UserMapper.dtoToEntity(userDto));
        }
        return ResponseEntity.badRequest()
                .header("Error", "Invalid login")
                .build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    private ResponseEntity deleteUser (@RequestBody UserDto userDto) {
        if (userDto != null && userDto.isValidLogin()) {
            userService.deleteUser(userDto.login());
        }
        return ResponseEntity.badRequest()
                .header("Error", "Invalid login")
                .build();
    }

}
