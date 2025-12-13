package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserService {
    public UserDto create(User user);

    public UserDto update(User user);

    public List<UserDto> findAll();

    public UserDto findById(Long id);

    public boolean deleteById(Long id);
}
