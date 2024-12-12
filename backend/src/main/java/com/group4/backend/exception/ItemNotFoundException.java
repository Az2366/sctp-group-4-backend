package com.group4.backend.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Could not find item with id: " + id);
    }

}
