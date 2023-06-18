package ru.itmo.web.hw4.model;

public enum Color {
    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF");

    public String getCode() {
        return code;
    }

    private final String code;

    Color(String code) {
        this.code = code;
    }
}
