package com.finalassessment.ubinge.model;

import java.util.HashSet;
import java.util.Set;

public class Customer extends GeneralDetails {
    Set<Order> orders = new HashSet<>();
}
