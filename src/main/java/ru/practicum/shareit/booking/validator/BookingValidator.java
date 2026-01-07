package ru.practicum.shareit.booking.validator;

import jakarta.validation.ValidationException;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;

public class BookingValidator {
    public static Booking validateBooking(Booking booking, LocalDateTime created) {
        validateBookingTime(booking, created);
        validateItem(booking);

        return booking;
    }

    public static void validateBookingTime(Booking booking, LocalDateTime created) {
        LocalDateTime start = booking.getStart();
        LocalDateTime end = booking.getEnd();

        if (start.isAfter(end) || start.equals(end)) {
            throw new ValidationException("Начало бронирования " + start
                    + " не может быть позже окончания бронирования " + end);
        }

        if (start.isBefore(created)) {
            throw new ValidationException("Начало бронирования не может быть раньше текущего времени");
        }

        if (end.isBefore(created)) {
            throw new ValidationException(("Конец бронирования не может быть раньше текущего времени"));
        }
    }

    public static void validateItem(Booking booking) {
        if (!booking.getItem().getAvailable()) {
            throw new ValidationException("Предмет недоступен для броинрования");
        }
    }
}
