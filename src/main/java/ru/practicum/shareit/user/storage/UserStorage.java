package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    public User save(User user);

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User update(User newUser);

    public boolean delete(Long id);
}
