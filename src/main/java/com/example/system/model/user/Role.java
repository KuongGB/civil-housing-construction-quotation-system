package com.example.system.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    CUSTOMER(Collections.emptySet()),
    MANAGER(
            Set.of(
                    Permission.MANAGER_FULLACCSESS
            )
    ),
    ADMIN(
            Set.of(
                    Permission.ADMIN_FULLACCESS,
                    Permission.MANAGER_FULLACCSESS
            )
    );
    @Getter
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
