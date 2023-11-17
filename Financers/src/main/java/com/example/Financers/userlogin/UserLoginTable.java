package com.example.Financers.userlogin;

import com.example.Financers.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = UserLoginTable.ENTITY_NAME)
@Table(name = UserLoginTable.TABLE_NAME)
@Getter
@Setter
@ToString
public class UserLoginTable {
    public static final String TABLE_NAME = "userLoginTable";
    public static final String ENTITY_NAME = "UserLoginTable";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private User user;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String salt;
    @NotBlank
    @NotNull
    private String hash;
}
