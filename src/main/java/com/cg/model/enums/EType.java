package com.cg.model.enums;

public enum EType {
    CAROM("CAROM"),
    POOL("POOL");

    private final String value;

    EType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
