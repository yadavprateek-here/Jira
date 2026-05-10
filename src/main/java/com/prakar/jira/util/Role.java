package com.prakar.jira.util;


public enum Role {
    EMPLOYEE(1),
    MANAGER(2),
    HR(3),
    ADMIN(4);

    private final int level;

    Role(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
