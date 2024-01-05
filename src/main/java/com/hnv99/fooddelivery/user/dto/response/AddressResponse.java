package com.hnv99.fooddelivery.user.dto.response;

import com.hnv99.fooddelivery.user.domain.Address;

public record AddressResponse(
        Long id,
        String address,
        String addressAlias
) {
    public static AddressResponse from(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getAddress(),
                address.getAddressAlias()
        );
    }
}
