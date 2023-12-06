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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userTable")
public class UserTable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @JsonProperty("login")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @JsonProperty("password")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty("email")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonProperty("name")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("surname")
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Column(name = "surname")
    private String surname;

    @Column(name = "createdAt")
    @CreationTimestamp
    @JsonIgnore
    private Long createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    @JsonIgnore
    private Long updatedAt;

}
