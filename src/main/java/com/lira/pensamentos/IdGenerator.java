package com.lira.pensamentos;

public class IdGenerator {
    private static int id = 3;
    public static int id() {
        return ++id;
    }
}
