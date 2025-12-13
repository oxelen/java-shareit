package ru.practicum.shareit.exception;

public class NullPathVariableException extends ConditionsNotMetException{
    public NullPathVariableException() {
        super("Параметр запроса не может быть null");
    }
}
