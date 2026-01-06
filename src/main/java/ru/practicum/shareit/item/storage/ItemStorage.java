package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {
    public Item save(Item item);

    public Optional<Item> findById(Long itemId);

    public Item update(Item newItem);

    public List<Item> findAll();
}
