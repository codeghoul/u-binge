package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public abstract class GeneralDetailsDTO extends BaseEntityDTO {
    @NotBlank(message = "Name field cannot be null.")
    private String name;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter a valid Phone Number.")
    @NotNull(message = "Phone Number cannot be null.")
    private String phoneNo;

    @NotNull(message = "Email field cannot be null.")
    @Email(message = "Enter a valid Email address.")
    private String email;
}
