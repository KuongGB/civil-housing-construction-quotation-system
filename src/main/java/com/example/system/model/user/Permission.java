package com.example.system.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_FULLACCESS("admin:fullaccess"),
    MANAGER_FULLACCSESS("manager:fullaccess")
    ;
    @Getter
    private final String permission;
}
