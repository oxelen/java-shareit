package ru.practicum.shareit.request.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */

@Data
public class ItemRequest {
    @PositiveOrZero(message = "Id должен быть больше или равен 0")
    private Long id;

    @NotBlank(message = "Описание не может быть пустым")
    private String description;

    @NotNull(message = "Пользователь не может быть null")
    private User requester;

    @NotNull(message = "Дата создания запроса не может быть пустой")
    private LocalDateTime created;
}
