package com.restTracker.restTracker.model;

import com.restTracker.restTracker.model.jwt.JwtRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {
    private final JwtRequest user;
    private boolean authentication = true;

    public UserAuthentication(JwtRequest user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authentication = b;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
