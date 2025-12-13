package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DuplicatedDataException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    @Override
    public UserDto create(User user) {
        if (containsEmail(user)) {
            throw new DuplicatedDataException("Пользователь с email " + user.getEmail() + " уже существует.");
        }

        return UserMapper.toUserDto(userStorage.save(user));
    }

    @Override
    public UserDto update(User newUser) {
        Long id = newUser.getId();

        String oldEmail = findById(id).getEmail();

        if (!oldEmail.equals(newUser.getEmail()) && containsEmail(newUser)) {
            throw new DuplicatedDataException("Email " + newUser.getEmail() + " уже занят.");
        }

        return UserMapper.toUserDto(userStorage.update(newUser));
    }

    @Override
    public List<UserDto> findAll() {
        return userStorage.findAll().stream().map(UserMapper::toUserDto).toList();
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.toUserDto(
                userStorage.findById(id).orElseThrow(()
                        -> new NotFoundException("Пользователь с id " + id + " не найден"))
        );
    }

    @Override
    public boolean deleteById(Long id) {
        findById(id);

        return userStorage.delete(id);
    }

    private boolean containsEmail(User user) {
        String currentUserEmail = user.getEmail();
        List<String> emails = userStorage.findAll().stream()
                .map(User::getEmail)
                .toList();

        return emails.contains(currentUserEmail);
    }
}
