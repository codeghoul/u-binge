package com.finalassessment.ubinge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User extends BaseEntity {
    @Column(name = "email", nullable = false, length = 32)
    String email;
    String password;
    Role role;
}
