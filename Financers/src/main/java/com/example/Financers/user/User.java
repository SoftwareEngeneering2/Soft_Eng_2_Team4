package com.example.Financers.user;

import com.example.Financers.userlogin.UserLoginTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = User.ENTITY_NAME)
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@ToString
public class User {
    public static final String TABLE_NAME = "userTable";
    public static final String ENTITY_NAME = "UserTable";
    public static final String REFERENCE_COLUMN = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private UserLoginTable userLoginTable;
}
