package com.zaritsky;

public class Main {

    public static void main(String[] args) {
        PermissionsKeeper keeper = new PermissionsKeeper();
        keeper.attach(new SmsChecker());

        keeper.setPermission(true);
    }
}
