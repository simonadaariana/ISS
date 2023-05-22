package com.example.proiect.configuration;

import java.util.Set;

public enum ApplicationUserPermission {
    ADMIN_REGISTER("admin:register"),
    TESTER_ADD_BUG("tester:add-bug"),
    TESTER_VIEW_LIST("tester:view-list"),
    PROGRAMMER_VIEW_LIST("programmer:view-list"),
    PROGRAMMER_SOLVE_BUG("programmer:solve-bug");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }

}
