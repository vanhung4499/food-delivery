package com.hnv99.fooddelivery.menu.domain;

import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.hnv99.fooddelivery.shop.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuValidator {
    public void validateShopId(
            Long shopId,
            Menu menu
    ) {
        Shop shop = menu.getShop();
        if (!shopId.equals(shop.getId())) {
            throw new InvalidValueException(ErrorCode.SHOP_BAD_REQUEST);
        }
    }

    public void validateMenuId(
            Long menuId,
            MenuOptionGroup menuOptionGroup
    ) {
        Menu menu = menuOptionGroup.getMenu();
        if (!menuId.equals(menu.getId())) {
            throw new InvalidValueException(ErrorCode.MENU_BAD_REQUEST);
        }
    }

    public void validateMenuOptionGroupId(
            Long menuOptionGroupId,
            MenuOption menuOption
    ) {
        MenuOptionGroup menuOptionGroup = menuOption.getMenuOptionGroup();
        if (!menuOptionGroupId.equals(menuOptionGroup.getId())) {
            throw new InvalidValueException(ErrorCode.MENU_OPTION_GROUP_BAD_REQUEST);
        }
    }
}
