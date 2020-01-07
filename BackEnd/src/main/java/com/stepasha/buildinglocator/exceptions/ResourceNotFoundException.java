package com.stepasha.buildinglocator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO 3b custom exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException(String message,
                                     Throwable cause)
    {
        super(message,
                cause);
    }
}
