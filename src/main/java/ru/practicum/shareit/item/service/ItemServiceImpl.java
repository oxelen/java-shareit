package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConditionsNotMetException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStorage itemStorage;
    private final UserStorage userStorage;

    @Override
    public ItemDto create(ItemDto itemDto, Long userId) {
        User user = getUser(userId);
        Item item = ItemMapper.toItem(itemDto, null, user, null);

        return ItemMapper.toItemDto(itemStorage.save(item));
    }

    @Override
    public ItemDto update(Long itemId, ItemDto newItemDto, Long userId) {
        Item oldItem = getItem(itemId);
        User user = getUser(userId);

        if (!oldItem.getOwner().getId().equals(userId)) {
            throw new ConditionsNotMetException("Пользователь с id + " + userId
                    + " не является владельцем вещи с id " + itemId + ".");
        }

        Item newItem = ItemMapper.toItem(newItemDto, itemId, user, oldItem.getRequest());
        if (newItem.getName() == null) {
            newItem.setName(oldItem.getName());
        }
        if (newItem.getDescription() == null) {
            newItem.setDescription(oldItem.getDescription());
        }

        return ItemMapper.toItemDto(itemStorage.update(newItem));
    }

    @Override
    public ItemDto findById(Long itemId) {
        return ItemMapper.toItemDto(getItem(itemId));
    }

    @Override
    public List<ItemDto> findAllByOwner(Long ownerId) {
        return itemStorage.findAll()
                .stream()
                .filter((item) -> item.getOwner().getId().equals(ownerId))
                .map(ItemMapper::toItemDto)
                .toList();
    }

    @Override
    public List<ItemDto> findBySearch(String text) {
        if (text.isEmpty()) {
            return List.of();
        }

        return itemStorage.findAll().stream()
                .filter((item) -> (item.getName().toUpperCase().contains(text.toUpperCase())
                        || item.getDescription().toUpperCase().contains(text.toUpperCase()))
                        && item.getAvailable())
                .map(ItemMapper::toItemDto)
                .toList();
    }

    private Item getItem(Long itemId) {
        return itemStorage.findById(itemId).orElseThrow(()
                -> new NotFoundException("Предмет с id " + itemId + " не найден"));
    }

    private User getUser(Long id) {
        return userStorage.findById(id).orElseThrow(()
                -> new NotFoundException("Пользователь с id " + id + " не найден"));
    }
}
