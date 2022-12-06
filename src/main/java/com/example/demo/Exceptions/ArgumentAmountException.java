package com.example.demo.Exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ArgumentAmountException extends ResponseStatusException {

    public ArgumentAmountException(HttpStatusCode status) {
        super(status);
    }

    public ArgumentAmountException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public ArgumentAmountException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public ArgumentAmountException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected ArgumentAmountException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
