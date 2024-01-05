package com.hnv99.fooddelivery.menu.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.hnv99.fooddelivery.shop.domain.Shop;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {
    public static final int MIN_PRICE = 0;

    public static final int MAX_NAME_LENGTH = 30;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "popularity", nullable = false, columnDefinition = "BIT(1)")
    private boolean popularity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MenuStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

    @Builder
    public Menu(
            String name,
            int price,
            boolean popularity
    ) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
        this.popularity = popularity;
        this.status = MenuStatus.UNSELLABLE;
    }

    public void attachShop(Shop shop) {
        if (this.shop != null) {
            this.shop.removeMenu(this);
        }
        this.shop = shop;

        shop.addMenu(this);
    }

    public void addMenuOptionGroup(MenuOptionGroup menuOptionGroup) {
        if (!menuOptionGroups.contains(menuOptionGroup)) {
            this.menuOptionGroups.add(menuOptionGroup);
        }
    }

    public void removeMenuOptionGroup(MenuOptionGroup menuOptionGroup) {
        this.menuOptionGroups.remove(menuOptionGroup);
    }

    private void validatePrice(int price) {
        if (price < MIN_PRICE) {
            throw new InvalidValueException(ErrorCode.MENU_PRICE_BAD_REQUEST);
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidValueException(ErrorCode.MENU_NAME_BAD_REQUEST);
        }
    }

    public void updateStatus(MenuStatus status) {
        this.status = status;
    }

    public void updateMenuInfo(
            String name,
            int price
    ) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

}
