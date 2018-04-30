package com.zaritsky;

public class PermissionsKeeper extends Subject {
    private boolean permission;

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
        notify(permission);
    }
}
