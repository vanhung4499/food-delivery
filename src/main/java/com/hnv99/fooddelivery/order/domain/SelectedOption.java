package com.hnv99.fooddelivery.order.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.BusinessException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.menu.domain.MenuOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "selected_options")
public class SelectedOption extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id", nullable = false)
    private MenuOption menuOption;

    private SelectedOption(
            MenuOption menuOption
    ) {
        validateMenuOption(menuOption);
        this.menuOption = menuOption;
    }

    public void attachTo(OrderItem orderItem) {
        if (this.orderItem != null) {
            orderItem.removeSelectedOption(this);
        }
        this.orderItem = orderItem;
        orderItem.addSelectedOption(this);
    }

    public static List<SelectedOption> from(List<MenuOption> menuOptions) {
        return menuOptions.stream()
                .map(SelectedOption::new)
                .toList();
    }

    private void validateMenuOption(MenuOption menuOption) {
        if (menuOption == null) {
            throw new BusinessException(ErrorCode.MENU_OPTION_BAD_REQUEST);
        }
    }
}
