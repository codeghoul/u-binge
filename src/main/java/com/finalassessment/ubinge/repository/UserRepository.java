package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
