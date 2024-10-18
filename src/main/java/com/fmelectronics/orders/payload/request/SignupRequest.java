package com.fmelectronics.orders.payload.request;

import com.fmelectronics.orders.models.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Data
//@Getter @Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class SignupRequest {
    @NotEmpty(message = "Username Required")
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty(message = "Email Required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Size(max = 50)
    @Email
    private String email;

    @NotEmpty(message = "Password Required")
    @Size(min = 6, max = 40)
    private String password;

//    @NotBlank(message = "Gender required")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- ]?\\d{3}[- ]?\\d{4}$")
    @NotEmpty(message = "Phone required")
    private String phone_number;

    private Set<String> role;

}
