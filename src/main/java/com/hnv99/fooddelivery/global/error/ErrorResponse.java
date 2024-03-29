package com.hnv99.fooddelivery.global.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<FieldErrorResponse>  errors;

    private final String code;

    private final String message;

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.name(),
                null,
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(
                errorCode.name(),
                FieldErrorResponse.from(bindingResult),
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldErrorResponse {
        private final String field;
        private final String value;
        private final String reason;

        private static List<FieldErrorResponse> from(BindingResult bindingResult) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldErrorResponse(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
        }
    }
}
