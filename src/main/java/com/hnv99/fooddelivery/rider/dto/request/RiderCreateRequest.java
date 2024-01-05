package com.hnv99.fooddelivery.rider.dto.request;

import com.hnv99.fooddelivery.rider.domain.Rider;
import com.hnv99.fooddelivery.global.util.PhonePolicy;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.rider.domain.Rider.*;

public record RiderCreateRequest(
        @Size(max = NAME_MAX_LENGTH, message = "rider name can be up to {max} characters long")
        @NotBlank(message = "rider name cannot be empty")
        String name,

        @Size(max = PHONE_MAX_LENGTH, message = "phone can be up to {max} characters long")
        @NotBlank(message = "phone cannot be empty")
        @Pattern(regexp = PhonePolicy.PHONE_PATTERN, message = "It must be in phone number format.")
        String phone
) {
    public Rider toEntity() {
        return new Rider(name, phone);
    }
}
