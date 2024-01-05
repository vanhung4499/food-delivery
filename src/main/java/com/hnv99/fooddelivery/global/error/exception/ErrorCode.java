package com.hnv99.fooddelivery.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR("COMMON_001", "Internal Server Error"),
    INVALID_REQUEST("COMMON_002", "Invalid request"),

    // User
    USER_NOT_FOUND("USER_001", "User not found"),
    USER_BAD_REQUEST("USER_002", "Invalid user"),
    USER_LOGIN_FAIL("USER_003", "Login information does not match"),
    USER_ADDRESS_NOT_FOUND("USER_004", "Address not found"),
    USER_ADDRESS_NOT_MATCH("USER_005", "Address does not match"),
    USER_ADDRESS_BAD_REQUEST("USER_006", "Invalid address"),
    USER_ADDRESS_ALIAS_BAD_REQUEST("USER_007", "Invalid address alias"),
    USER_EMAIL_BAD_REQUEST("USER_008", "Invalid email"),
    USER_PASSWORD_BAD_REQUEST("USER_009", "Invalid password"),
    USER_NAME_BAD_REQUEST("USER_010", "Invalid user name"),
    USER_PHONE_BAD_REQUEST("USER_011", "Invalid user phone number"),
    USER_BIRTHDAY_BAD_REQUEST("USER_012", "Invalid user birthday"),

    // Menu
    MENU_NOT_FOUND("MENU_001", "Menu not found"),
    MENU_BAD_REQUEST("MENU_002", "Invalid menu"),
    MENU_OPTION_NOT_FOUND("MENU_003", "Menu option not found"),
    MENU_OPTION_GROUP_NOT_FOUND("MENU_004", "Menu option group not found"),
    MENU_OPTION_BAD_REQUEST("MENU_005", "Invalid menu option"),
    MENU_OPTION_GROUP_BAD_REQUEST("MENU_006", "Invalid menu option group"),
    MENU_PRICE_BAD_REQUEST("MENU_007", "Invalid menu price"),
    MENU_NAME_BAD_REQUEST("MENU_008", "Invalid menu name"),
    MENU_OPTION_NAME_BAD_REQUEST("MENU_009", "Invalid menu option name"),
    MENU_OPTION_GROUP_NAME_BAD_REQUEST("MENU_010", "Invalid menu option group name"),

    // Order
    ORDER_NOT_FOUND("ORDER_001", "Order not found"),
    ORDER_BAD_REQUEST("ORDER_002", "Invalid order"),
    ORDER_STATUS_BAD_REQUEST("ORDER_003", "Invalid order status"),
    ORDER_ADDRESS_BAD_REQUEST("ORDER_004", "Invalid order address"),
    ORDER_REQUIREMENT_BAD_REQUEST("ORDER_005", "Invalid order requirements"),
    ORDER_ITEM_PRICE_BAD_REQUEST("ORDER_006", "Invalid order item price"),
    ORDER_ITEM_QUANTITY_BAD_REQUEST("ORDER_007", "Invalid order item quantity"),
    ORDER_SHOP_NOT_MATCH("ORDER_008", "Order does not match shop ID"),

    // Shop
    SHOP_NOT_FOUND("SHOP_001", "Shop not found"),
    SHOP_BAD_REQUEST("SHOP_002", "Invalid shop"),
    SHOP_NAME_BAD_REQUEST("SHOP_003", "Invalid shop name"),
    SHOP_ADDRESS_BAD_REQUEST("SHOP_004", "Invalid shop address"),
    SHOP_PHONE_BAD_REQUEST("SHOP_005", "Invalid shop number"),
    SHOP_DELIVERY_TIP_BAD_REQUEST("SHOP_006", "Invalid shop delivery tip"),
    SHOP_OPENING_TIME_BAD_REQUEST("SHOP_007", "Invalid shop opening time"),
    SHOP_CLOSING_TIME_BAD_REQUEST("SHOP_008", "Invalid shop closing time"),
    SHOP_SORT_BAD_REQUEST("SHOP_009", "Invalid shop sorting condition"),

    // Delivery
    DELIVERY_NOT_FOUND("DELIVERY_001", "Delivery not found"),
    DELIVERY_BAD_REQUEST("DELIVERY_002", "Invalid delivery"),
    DELIVERY_RIDER_NOT_FOUND("DELIVERY_003", "Delivery rider not found"),
    DELIVERY_RIDER_BAD_REQUEST("DELIVERY_004", "Invalid delivery rider");

    private final String code;
    private final String message;
}
