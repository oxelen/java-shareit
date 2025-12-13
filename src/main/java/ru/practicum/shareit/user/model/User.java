package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */

@Data
@Builder
public class User {
    @PositiveOrZero(message = "Id должен быть больше или равен 0")
    private Long id;

    @Email(message = "Email указан не по форме")
    @NotBlank(message = "Неверная форма email")
    private String email;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;
}
