package com.stepasha.buildinglocator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO 3 custom Exceptions
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ResourceFoundException(String message)
    {
        super(message);
    }

    public ResourceFoundException(String message,
                                  Throwable cause)
    {
        super(message,
                cause);
    }
}
