package ru.practicum.shareit.booking.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class Booking {
    @PositiveOrZero(message = "Id должен быть больше или равен 0")
    private Long id;

    private LocalDateTime start;
    private LocalDateTime end;

    @NotNull(message = "Предмет не может быть null")
    private Item item;

    @NotNull(message = "Пользователь не может быть null")
    private User booker;

    @NotNull(message = "Статус не может быть null")
    private BookingStatus status;
}
