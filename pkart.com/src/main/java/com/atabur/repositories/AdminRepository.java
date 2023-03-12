package com.atabur.repositories;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByEmail(@NotNull String username);

	Optional<Admin> findByMobile(@NotNull String mobile);

}
