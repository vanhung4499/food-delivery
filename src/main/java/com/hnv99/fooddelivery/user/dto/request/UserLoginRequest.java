package com.hnv99.fooddelivery.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.user.domain.User.*;

public record UserLoginRequest(
        @Size(max = EMAIL_MAX_LENGTH, message = "The login id can be up to {max} characters.")
        @NotBlank(message = "The login id cannot be empty.")
        String email,

        @Size(max = PASSWORD_MAX_LENGTH, message = "The password can be up to {max} characters.")
        @NotBlank(message = "The password cannot be empty.")
        String password
) {
}
