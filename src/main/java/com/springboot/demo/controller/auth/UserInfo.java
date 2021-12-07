package com.springboot.demo.controller.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"authorities","roles"},ignoreUnknown = true)
public class UserInfo extends User implements UserDetails, Serializable {

    private Set<String> roles;

    public UserInfo() {

    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    //授权信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities =new HashSet<>();
        if(roles!=null && roles.size()>0){
            roles.stream().forEach(item->{
                authorities.add(new SimpleGrantedAuthority(item));
            });
        }
        return authorities;
    }

    //用户名
    @Override
    public String getUsername() {
        return super.getUserName();
    }

    //密码
    @Override
    public String getPassword() {
        return super.getUserPassword();
    }

    //账号是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }


}
