package github.alexzhirkevich.studentbsuby.util.exceptions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/exceptions/FailResponseException;", "Lgithub/alexzhirkevich/studentbsuby/util/exceptions/NetworkResponseException;", "code", "", "(I)V", "getCode", "()I", "app_release"})
public final class FailResponseException extends github.alexzhirkevich.studentbsuby.util.exceptions.NetworkResponseException {
    private final int code = 0;
    
    public FailResponseException(int code) {
        super();
    }
    
    public final int getCode() {
        return 0;
    }
}