package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
