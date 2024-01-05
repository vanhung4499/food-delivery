package com.hnv99.fooddelivery.rider.domain;


import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.hnv99.fooddelivery.global.util.PhonePolicy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "riders")
public class Rider extends BaseEntity {

    public static final int NAME_MAX_LENGTH = 10;

    public static final int PHONE_MAX_LENGTH = 15;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "phone", nullable = false)
    String phone;

    public Rider(
            String name,
            String phone
    ) {
        validateName(name);
        validatePhone(phone);
        this.name = name;
        this.phone = phone;
    }

    private void validateName(String name) {
        if (name == null || name.length() > NAME_MAX_LENGTH) {
            throw new InvalidValueException(ErrorCode.USER_NAME_BAD_REQUEST);
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.length() > PHONE_MAX_LENGTH || !PhonePolicy.matches(phone)) {
            throw new InvalidValueException(ErrorCode.USER_PHONE_BAD_REQUEST);
        }
    }
}
