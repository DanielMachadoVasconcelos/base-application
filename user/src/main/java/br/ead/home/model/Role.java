package br.ead.home.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    WRITE_PRIVILEGE, READ_PRIVILEGE;

    @Override
    public String getAuthority() {
        return name();
    }
}
