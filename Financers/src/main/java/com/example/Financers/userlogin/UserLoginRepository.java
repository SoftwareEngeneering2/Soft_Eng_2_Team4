package com.example.Financers.userlogin;

import com.example.Financers.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginTable, User> {
    @Query
    boolean existsByEmail(String email);
    @Query("SELECT user FROM UserLoginTable WHERE email = :email AND hash = :hash")
    Optional<User> getUser(@Param("email") String email, @Param("hash") String hash);
    @Query("SELECT salt FROM UserLoginTable ult WHERE email = :email")
    Optional<String> getSalt(@Param("email") String email);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE UserLoginTable SET hash = :hash WHERE email = :email")
    void resetHash(@Param("email") String email, @Param("hash") String hash);
}

