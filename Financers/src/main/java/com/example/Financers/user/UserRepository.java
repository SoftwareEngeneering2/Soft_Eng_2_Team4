package com.example.Financers.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserSignUpModel,String> {
    @Query("SELECT s FROM UserSignUpModel s WHERE s.email = ?1")
    Optional<UserSignUpModel> findUserByEmail(String email);
}
