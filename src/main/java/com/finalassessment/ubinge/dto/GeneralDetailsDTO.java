package com.finalassessment.ubinge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

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

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @JsonIgnore
    private String password;
}
