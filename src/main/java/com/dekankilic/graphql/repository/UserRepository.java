package com.dekankilic.graphql.repository;

import com.dekankilic.graphql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
