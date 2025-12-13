package ru.practicum.shareit.request.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemRequestMapper {
    public ItemRequestDto toItemRequestDto(ItemRequest request) {
        return new ItemRequestDto(
                request.getDescription(),
                request.getRequester().getId()
        );
    }
}
