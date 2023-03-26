package com.normal;

import java.util.Random;

public enum DirType {
    L, R, U, D;
    private static final Random random = new Random();

    public static DirType randomDir() {
        return DirType.values()[random.nextInt(DirType.values().length)];
    }
}
