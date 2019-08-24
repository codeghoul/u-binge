package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends GeneralDetails {
    @OneToMany(mappedBy = "customer")
    @JsonManagedReference(value = "user-orders")
    private Set<Order> orders = new HashSet<>();
}
