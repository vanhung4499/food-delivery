package com.hnv99.fooddelivery.menu.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "menu_options")
public class MenuOption extends BaseEntity {
    public static final int MIN_PRICE = 0;

    public static final int MAX_NAME_LENGTH = 30;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_group_id")
    private MenuOptionGroup menuOptionGroup;

    public MenuOption(
            String name,
            int price
    ) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    public void attachMenuOptionGroup(MenuOptionGroup menuOptionGroup) {
        if (this.menuOptionGroup != null) {
            this.menuOptionGroup.removeMenuOption(this);
        }
        this.menuOptionGroup = menuOptionGroup;

        menuOptionGroup.addMenuOption(this);
    }

    public void updateOptionInfo(
            String name,
            int price
    ) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price < MIN_PRICE) {
            throw new InvalidValueException(ErrorCode.MENU_PRICE_BAD_REQUEST);
        }
    }

    private void validateName(String name) {
        if (name == null || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidValueException(ErrorCode.MENU_NAME_BAD_REQUEST);
        }
    }
}
