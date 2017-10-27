package es.randomco.randomapp.domain.exceptions;

import android.support.annotation.NonNull;

public class DbException extends RuntimeException {

    @NonNull
    private final String code;
    @NonNull
    private final String errorMessage;

    public DbException(@NonNull String code, @NonNull String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getErrorMessage() {
        return errorMessage;
    }
}
