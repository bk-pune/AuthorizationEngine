package com.bk.exception;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 20/06/22
 */
public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }
}
