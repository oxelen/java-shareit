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
    public User create(User user) {
        if (containsEmail(user)) {
            throw new DuplicatedDataException("Пользователь с email " + user.getEmail() + " уже существует.");
        }

        return userStorage.save(user);
        //return UserMapper.toUserDto(userStorage.save(user));
    }

    @Override
    public User update(Long userId, UserDto newUserDto) {
        User oldUser = getUser(userId);
        User newUser = UserMapper.toUser(newUserDto, userId);

        if (newUser.getName() == null) {
            newUser.setName(oldUser.getName());
        }
        if (newUser.getEmail() == null) {
            newUser.setEmail(oldUser.getEmail());
        }

        String oldEmail = oldUser.getEmail();

        if (!oldEmail.equals(newUser.getEmail()) && containsEmail(newUser)) {
            throw new DuplicatedDataException("Email " + newUser.getEmail() + " уже занят.");
        }

        return userStorage.update(newUser);
        //return UserMapper.toUserDto(userStorage.update(newUser));
    }

    @Override
    public List<User> findAll() {
        return userStorage.findAll();
    }

    @Override
    public User findById(Long id) {
        return getUser(id);
    }

    @Override
    public boolean deleteById(Long id) {
        findById(id);

        return userStorage.delete(id);
    }

    private User getUser(Long id) {
        return userStorage.findById(id).orElseThrow(()
                -> new NotFoundException("Пользователь с id " + id + " не найден"));
    }

    private boolean containsEmail(User user) {
        String currentUserEmail = user.getEmail();
        List<String> emails = userStorage.findAll().stream()
                .map(User::getEmail)
                .toList();

        return emails.contains(currentUserEmail);
    }
}
