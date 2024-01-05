package com.hnv99.fooddelivery.user.dto.response;

import java.util.List;

public record UserLoginResponse (
        String token,
        Long userId,
        List<String> roles
) { }
