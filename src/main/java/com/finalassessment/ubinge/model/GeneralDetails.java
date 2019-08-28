package com.finalassessment.ubinge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralDetails extends BaseEntity {
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 12)
    private String phoneNo;

    @Column(name = "email", nullable = false, length = 32)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;
}
