package com.hnv99.fooddelivery.shop.dto.request;

import com.hnv99.fooddelivery.global.common.ValidEnum;
import com.hnv99.fooddelivery.global.util.PhonePolicy;
import com.hnv99.fooddelivery.shop.domain.Category;
import com.hnv99.fooddelivery.shop.domain.Shop;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import java.time.LocalTime;

import static com.hnv99.fooddelivery.shop.domain.Shop.*;

public record ShopCreateRequest (
        @Size(max = MAX_NAME_LENGTH, message = "The shop name can be up to {max} characters.")
        @NotBlank(message = "The shop name cannot be empty.")
        String name,

        @ValidEnum(enumClass = Category.class)
        String category,

        @Size(max = MAX_ADDRESS_LENGTH, message = "The address can be up to {max} characters.")
        @NotBlank(message = "The address cannot be empty.")
        String address,

        @Size(max = MAX_PHONE_LENGTH, message = "The phone number can be up to {max} characters.")
        @NotBlank(message = "The phone number cannot be empty.")
        @Pattern(regexp = PhonePolicy.PHONE_PATTERN, message = "The phone number must be in the correct format.")
        String phone,

        String content,

        @PositiveOrZero(message = "The delivery tip must be at least 0.")
        int deliveryTip,

        @NotNull(message = "The opening time cannot be empty.")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime openingTime,

        @NotNull(message = "The closing time cannot be empty.")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime closingTime
) {

    public Shop toEntity() {
        return Shop.builder()
                .name(this.name)
                .category(Category.valueOf(this.category))
                .address(this.address)
                .phone(this.phone)
                .content(this.content)
                .deliveryTip(this.deliveryTip)
                .openingTime(this.openingTime)
                .closingTime(this.closingTime)
                .build();
    }
}
