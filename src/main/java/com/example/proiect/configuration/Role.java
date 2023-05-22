package com.example.proiect.configuration;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.proiect.configuration.ApplicationUserPermission.*;


public enum Role {
    ADMIN(Sets.newHashSet(ADMIN_REGISTER, TESTER_ADD_BUG, PROGRAMMER_SOLVE_BUG, TESTER_VIEW_LIST, PROGRAMMER_VIEW_LIST)),
    TESTER(Sets.newHashSet(TESTER_ADD_BUG)),
    PROGRAMMER(Sets.newHashSet(PROGRAMMER_SOLVE_BUG));

    private final Set<ApplicationUserPermission> permission;

    Role(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> collect = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        collect.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return collect;
    }
}
