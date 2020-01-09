package com.example.springdemo.dto.UsersDTOs;

import com.example.springdemo.entities.enums.UsersType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UsersSecurityDTO implements UserDetails {

    private String username;
    private String password;
    private UsersType role;
    private Collection<? extends GrantedAuthority> authorities;

    public UsersSecurityDTO(String username, String password, UsersType role,
                            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UsersType getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersSecurityDTO that = (UsersSecurityDTO) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                role == that.role &&
                Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, authorities);
    }
}
