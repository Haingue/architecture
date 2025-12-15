package com.haingue.tp1.CommunityBookstore.service.implement;

import com.haingue.tp1.CommunityBookstore.dto.UserDto;
import com.haingue.tp1.CommunityBookstore.dto.wrapper.PaginatedResponseDto;
import com.haingue.tp1.CommunityBookstore.exception.BadRequestException;
import com.haingue.tp1.CommunityBookstore.mapper.UserMapper;
import com.haingue.tp1.CommunityBookstore.model.User;
import com.haingue.tp1.CommunityBookstore.repository.UserRepository;
import com.haingue.tp1.CommunityBookstore.service.crud.UserService;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(@Nonnull UserDto userDto) {
        if (userDto.password() == null || userDto.password().length() < 6) throw new BadRequestException();
        if (StringUtils.isEmpty(userDto.name())) throw new BadRequestException();

        User user = UserMapper.INSTANCE.toModel(userDto);
        user.setUuid(UUID.randomUUID());
        user = userRepository.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDto update(@Nonnull UserDto userDto) {
        if (userDto.password() == null || userDto.password().length() < 6) throw new BadRequestException();
        if (!this.userRepository.existsById(userDto.uuid())) throw new BadRequestException();
        User user = UserMapper.INSTANCE.toModel(userDto);
        user.setUuid(userDto.uuid());
        user = userRepository.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDto findOneById(@Nonnull UUID uuid) {
        User user = this.userRepository.findById(uuid).orElseThrow(BadRequestException::new);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public void delete(UUID uuid) {
        this.userRepository.deleteById(uuid);
    }

    @Override
    public PaginatedResponseDto<UserDto> findAll(int pageNumber, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
