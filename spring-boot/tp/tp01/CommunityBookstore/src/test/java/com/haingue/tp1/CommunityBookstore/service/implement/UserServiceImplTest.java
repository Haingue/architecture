package com.haingue.tp1.CommunityBookstore.service.implement;

import com.haingue.tp1.CommunityBookstore.dto.UserDto;
import com.haingue.tp1.CommunityBookstore.exception.BadRequestException;
import com.haingue.tp1.CommunityBookstore.mapper.UserMapper;
import com.haingue.tp1.CommunityBookstore.model.Role;
import com.haingue.tp1.CommunityBookstore.model.User;
import com.haingue.tp1.CommunityBookstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser_Success() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        UserDto userDto = new UserDto(uuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        User user = UserMapper.INSTANCE.toModel(userDto);
        user.setUuid(uuid);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDto result = userService.create(userDto);

        // Assert
        assertNotNull(result);
        assertEquals(userDto.name(), result.name());
        assertEquals(userDto.email(), result.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUser_InvalidPassword_ThrowsBadRequestException() {
        // Arrange
        UserDto invalidUserDto = new UserDto(UUID.randomUUID(), "John Doe", "john.doe@example.com", "123", Role.CUSTOMER);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> userService.create(invalidUserDto));
    }

    @Test
    public void testCreateUser_EmptyName_ThrowsBadRequestException() {
        // Arrange
        UserDto invalidUserDto = new UserDto(UUID.randomUUID(), "", "john.doe@example.com", "password123", Role.CUSTOMER);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> userService.create(invalidUserDto));
    }

    @Test
    public void testUpdateUser_Success() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        UserDto userDto = new UserDto(uuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        User user = UserMapper.INSTANCE.toModel(userDto);
        user.setUuid(uuid);
        when(userRepository.existsById(uuid)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDto result = userService.update(userDto);

        // Assert
        assertNotNull(result);
        assertEquals(userDto.name(), result.name());
        assertEquals(userDto.email(), result.email());
        verify(userRepository, times(1)).existsById(uuid);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_InvalidPassword_ThrowsBadRequestException() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        UserDto invalidUserDto = new UserDto(uuid, "John Doe", "john.doe@example.com", "123", Role.CUSTOMER);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> userService.update(invalidUserDto));
    }

    @Test
    public void testUpdateUser_UserNotFound_ThrowsBadRequestException() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        UserDto userDto = new UserDto(uuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        when(userRepository.existsById(uuid)).thenReturn(false);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> userService.update(userDto));
    }

    @Test
    public void testFindOneById_Success() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid, "John Doe", "john.doe@example.com", "password123", Role.CUSTOMER);
        when(userRepository.findById(uuid)).thenReturn(Optional.of(user));

        // Act
        UserDto result = userService.findOneById(uuid);

        // Assert
        assertNotNull(result);
        assertEquals(user.getName(), result.name());
        assertEquals(user.getEmail(), result.email());
    }

    @Test
    public void testFindOneById_UserNotFound_ThrowsBadRequestException() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        when(userRepository.findById(uuid)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> userService.findOneById(uuid));
    }
}