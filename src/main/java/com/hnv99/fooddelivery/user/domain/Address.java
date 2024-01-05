package com.hnv99.fooddelivery.user.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    public static final int MAX_ADDRESS_LENGTH = 50;

    public static final int MAX_ADDRESS_ALIAS_LENGTH = 10;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "addressAlias", nullable = false, length = MAX_ADDRESS_ALIAS_LENGTH)
    private String addressAlias;

    @Column(name = "address", nullable = false, length = MAX_ADDRESS_LENGTH)
    private String address;

    public Address(String addressAlias, String address) {
        validateAddressAlias(addressAlias);
        validateAddress(address);
        this.addressAlias = addressAlias;
        this.address = address;
    }

    public void attachToUser(User user) {
        if (this.user != null) {
            this.user.removeAddress(this);
        }
        this.user = user;
        user.addAddress(this);
    }

    public void updateAddress(
            String addressAlias,
            String address
    ) {
        validateAddressAlias(addressAlias);
        validateAddress(address);
        this.addressAlias = addressAlias;
        this.address = address;
    }

    private void validateAddress(String address) {
        if (address == null || address.isEmpty() || address.length() > MAX_ADDRESS_LENGTH) {
            throw new InvalidValueException(ErrorCode.USER_ADDRESS_BAD_REQUEST);
        }
    }

    private void validateAddressAlias(String addressAlias) {
        if (addressAlias == null || addressAlias.isEmpty() || addressAlias.length() > MAX_ADDRESS_ALIAS_LENGTH) {
            throw new InvalidValueException(ErrorCode.USER_ADDRESS_ALIAS_BAD_REQUEST);
        }
    }
}
