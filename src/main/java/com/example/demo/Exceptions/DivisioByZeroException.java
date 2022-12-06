package com.example.demo.Exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class DivisioByZeroException extends ResponseStatusException {

    private DivisioByZeroException(HttpStatusCode status) {
        super(status);
    }

    public DivisioByZeroException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    private DivisioByZeroException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    private DivisioByZeroException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    private DivisioByZeroException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
