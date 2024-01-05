package com.hnv99.fooddelivery.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.user.domain.User.PASSWORD_MAX_LENGTH;

public record ChangePasswordRequest(
    @Size(max = PASSWORD_MAX_LENGTH, message = "The password can be up to {max} characters.")
    @NotBlank(message = "The password cannot be empty.")
    String password,

    @Size(max = PASSWORD_MAX_LENGTH, message = "The password can be up to {max} characters.")
    @NotBlank(message = "The password cannot be empty.")
    String newPassword
) {}
