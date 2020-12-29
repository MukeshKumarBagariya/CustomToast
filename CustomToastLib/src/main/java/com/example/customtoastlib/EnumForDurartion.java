package com.example.customtoastlib;

public enum EnumForDurartion {
    SHORT(1), LONG(2);
    private int id;

    EnumForDurartion(int id) { }
    public int getId() {
        return id;
    }

}
