
package com.normal.FireStrategy;

public enum FireStrategyType {
    DEFAULT, AROUND;

    public static FireStrategyType getFireStrategyType(String str) {
        if (DEFAULT.name().equalsIgnoreCase(str)) {
            return DEFAULT;
        }
        if (AROUND.name().equalsIgnoreCase(str)) {
            return AROUND;
        }
        return null;
    }
}
