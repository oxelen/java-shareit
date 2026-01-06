package ru.practicum.shareit.validation;

import ru.practicum.shareit.exception.ConditionsNotMetException;

public class IdValidator {
    public static void validateId(Long... ids) {
        for (Long id : ids) {
            if (id < 0) {
                throw new ConditionsNotMetException("Id не может меньше 0");
            }
        }
    }
}
