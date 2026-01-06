package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryItemStorage implements ItemStorage {
    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Item save(Item item) {
        long id = createId();
        item.setId(id);
        items.put(id, item);

        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public Item update(Item newItem) {
        items.put(newItem.getId(), newItem);

        return newItem;
    }

    @Override
    public List<Item> findAll() {
        return items.values().stream().toList();
    }

    private long createId() {
        return items.keySet().stream().mapToLong(id -> id).max().orElse(-1) + 1;
    }
}
