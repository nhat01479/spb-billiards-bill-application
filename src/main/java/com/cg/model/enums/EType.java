package com.cg.model.enums;

public enum EType {
    CAROM("CAROM TABLE"),
    POOL("POOL TABLE");

    private final String value;

    EType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
