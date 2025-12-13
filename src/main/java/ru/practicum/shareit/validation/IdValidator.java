package ru.practicum.shareit.validation;

import ru.practicum.shareit.exception.ConditionsNotMetException;

public class IdValidator {
    public static void validateId(Long id) {
        if (id < 0) {
            throw new ConditionsNotMetException("id не может меньше 0");
        }
    }
}
