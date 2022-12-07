package com.javatech.lab8.pemissions;

public enum Role {
    REVIEWER("REVIEWER"),
    AUTHOR("AUTHOR"),
    ADMIN("ADMIN");

    String role;

    Role(String reviewer) {
        this.role = reviewer;
    }

    public static Role fromString(String text) {
        for (Role b : Role.values()) {
            if (b.role.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
