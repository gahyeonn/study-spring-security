package com.gahyeonn.ssiach11s1.repository;

import com.gahyeonn.ssiach11s1.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findOtpByUsername(String username);
}
