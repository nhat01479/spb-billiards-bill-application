package com.cg.model.enums;

public enum ECategory {
    DRINK("DRINK"),
    FOOD("FOOD");

    private final String value;

    ECategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
