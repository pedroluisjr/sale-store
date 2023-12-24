package com.salestore.salesstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salestore.salesstore.dto.UserRoleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "userTable") //Define o nome da entidade para utilização no código.
@Table(name = "userTable")
@Schema(name = "User")
public class UserTable implements UserDetails {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @JsonProperty("role")
    @Column(name = "role")
    private UserRoleDto role;

    @Column(name = "createdAt")
    @CreationTimestamp
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    @JsonIgnore
    private Date updatedAt;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRoleDto.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return login;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
