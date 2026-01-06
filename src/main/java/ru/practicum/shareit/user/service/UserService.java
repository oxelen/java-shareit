package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserService {
    public User create(User user);

    public User update(Long userId, UserDto newUserDto);

    public List<User> findAll();

    public User findById(Long id);

    public boolean deleteById(Long id);
}
