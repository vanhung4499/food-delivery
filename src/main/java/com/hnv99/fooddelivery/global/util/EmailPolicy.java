package com.hnv99.fooddelivery.global.util;

import java.util.regex.Pattern;

public class EmailPolicy {
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final Pattern EMAIL_MATCHER = Pattern.compile(EMAIL_PATTERN);

    public static boolean matches(String email) {
        return EMAIL_MATCHER.matcher(email).matches();
    }
}
