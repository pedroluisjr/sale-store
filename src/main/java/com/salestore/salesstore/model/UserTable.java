package com.salestore.salesstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userTable")
@Schema(name = "User")
public class UserTable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @JsonProperty("login")
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @JsonProperty("password")
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty("email")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonProperty("name")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @Column(name = "createdAt")
    @CreationTimestamp
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    @JsonIgnore
    private Date updatedAt;

}
