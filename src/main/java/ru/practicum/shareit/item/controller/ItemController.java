package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.validation.IdValidator;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemDto itemDto,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        IdValidator.validateId(userId);

        return itemService.create(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@PathVariable("itemId") Long itemId,
                          @RequestBody ItemDto itemDto,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        IdValidator.validateId(itemId, userId);

        return itemService.update(itemId, itemDto, userId);
    }

    @GetMapping("/{itemId}")
    public ItemDto findById(@PathVariable("itemId") Long itemId) {
        IdValidator.validateId(itemId);

        return itemService.findById(itemId);
    }

    @GetMapping
    public List<ItemDto> findAllByOwner(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        IdValidator.validateId(ownerId);

        return itemService.findAllByOwner(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> findBySearch(@RequestParam("text") String text) {
        return itemService.findBySearch(text);
    }
}
