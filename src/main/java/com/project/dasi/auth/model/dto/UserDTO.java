package com.project.dasi.auth.model.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO implements UserDetails {
    private int userCode;
    private String userId;
    private String password;
    private String name;
    private String birthDate;
    private String email;
    private String tel;
    private String gender;
    private java.util.Date joinDate;
    @Builder
    public UserDTO(int userCode, String userId, String password, String name, String birthDate, String email, String tel, String gender, java.util.Date joinDate) {
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.tel = tel;
        this.gender = gender;
        this.joinDate = joinDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return Collections.singletonList(new SimpleGrantedAuthority(this.userRole));
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // 시큐리티의 userName
    // -> 따라서 얘는 인증할 때 id를 봄
    @Override
    public String getUsername() {
        return this.userId;
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
}

