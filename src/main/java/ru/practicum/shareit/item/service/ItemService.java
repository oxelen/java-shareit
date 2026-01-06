package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    public ItemDto create(ItemDto item, Long userId);

    public ItemDto update(Long itemId, ItemDto itemDto, Long userId);

    public ItemDto findById(Long itemId);

    public List<ItemDto> findAllByOwner(Long ownerId);

    public List<ItemDto> findBySearch(String text);
}
