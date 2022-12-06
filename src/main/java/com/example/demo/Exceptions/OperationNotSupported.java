package com.example.demo.Exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class OperationNotSupported extends ResponseStatusException {

    public OperationNotSupported(HttpStatusCode status) {
        super(status);
    }

    public OperationNotSupported(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    private OperationNotSupported(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    private OperationNotSupported(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    private OperationNotSupported(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
