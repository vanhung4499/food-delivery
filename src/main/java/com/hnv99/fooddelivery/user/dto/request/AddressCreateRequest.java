package com.hnv99.fooddelivery.user.dto.request;

import com.hnv99.fooddelivery.user.domain.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.hnv99.fooddelivery.user.domain.Address.MAX_ADDRESS_ALIAS_LENGTH;
import static com.hnv99.fooddelivery.user.domain.Address.MAX_ADDRESS_LENGTH;

public record AddressCreateRequest(
    @Size(max = MAX_ADDRESS_ALIAS_LENGTH, message = "The address alias can be up to {max} characters.")
    @NotBlank(message = "The address alias cannot be empty.")
    String addressAlias,

    @Size(max = MAX_ADDRESS_LENGTH, message = "The address can be up to {max} characters.")
    @NotBlank(message = "The address cannot be empty.")
    String address
) {
    public Address toEntity() {
        return new Address(addressAlias, address);
    }
}
