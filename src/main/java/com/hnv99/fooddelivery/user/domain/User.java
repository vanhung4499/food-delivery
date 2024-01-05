package com.hnv99.fooddelivery.user.domain;

import com.hnv99.fooddelivery.global.common.BaseEntity;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.hnv99.fooddelivery.global.util.EmailPolicy;
import com.hnv99.fooddelivery.global.util.PhonePolicy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    public static final int EMAIL_MAX_LENGTH = 30;

    public static final int PASSWORD_MAX_LENGTH = 20;

    private static final int ENCODED_PASSWORD_LENGTH = 60;

    public static final int NAME_MAX_LENGTH = 10;

    public static final int PHONE_MAX_LENGTH = 15;

    @Column(name = "email", nullable = false, length = EMAIL_MAX_LENGTH)
    private String email;

    @Column(name = "password", nullable = false, length = ENCODED_PASSWORD_LENGTH)
    private String password;

    @Column(name = "name", nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(name = "phone", nullable = false, length = PHONE_MAX_LENGTH)
    private String phone;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    public User(
            String email,
            String password,
            String name,
            String phone,
            LocalDate birthday
    ) {
        validateEmail(email);
        validateName(name);
        validatePhone(phone);
        validateBirthDay(birthday);
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.level = Level.NEW;
        this.roles.add(Role.USER.name());
    }

    public static String password(String password, String encodedPassword) {
        validatePassword(password);
        return encodedPassword;
    }

    public void updateGrade(Level grade) {
        this.level = grade;
    }

    public void addAddress(Address address) {
        if (addresses.contains(address)) {
            return;
        }
        addresses.add(address);
    }

    public boolean removeAddress(Address address) {
        return addresses.remove(address);
    }

    public List<GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

    public void checkPassword(
            PasswordEncoder passwordEncoder,
            String credentials
    ) {
        if (!passwordEncoder.matches(credentials, this.password)) {
            throw new InvalidValueException(ErrorCode.USER_LOGIN_FAIL);
        }
    }

    public void updateInfo(
            String name,
            String phone,
            LocalDate birthday
    ) {
        validateName(name);
        validatePhone(phone);
        validateBirthDay(birthday);
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }

    public void changePassword(
            String password
    ) {
        this.password = password;
    }

    private void validateEmail(String email) {
        if (email == null || email.length() > EMAIL_MAX_LENGTH || !EmailPolicy.matches(email)) {
            throw new InvalidValueException(ErrorCode.USER_EMAIL_BAD_REQUEST);
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.length() > PASSWORD_MAX_LENGTH) {
            throw new InvalidValueException(ErrorCode.USER_PASSWORD_BAD_REQUEST);
        }
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

    private void validateBirthDay(LocalDate birthday) {
        if (birthday == null || birthday.isAfter(LocalDate.now())) {
            throw new InvalidValueException(ErrorCode.USER_BIRTHDAY_BAD_REQUEST);
        }
    }
}
