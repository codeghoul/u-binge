package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}