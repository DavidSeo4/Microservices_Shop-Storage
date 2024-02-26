package com.basic.SecurityJwt.SecurityJwt.model;

import java.util.Arrays;
import java.util.List;

public enum Role {

    EMPLOYEE(Arrays.asList(Permission.CAN_MANAGE_THE_SHOP)),

    EMPLOYER(Arrays.asList(Permission.CAN_MANAGE_THE_SHOP, Permission.CAN_MANAGE_THE_STORAGE));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

}
