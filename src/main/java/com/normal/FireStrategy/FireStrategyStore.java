package com.normal.FireStrategy;

public class FireStrategyStore {

    public static FireStrategy getFireStrategy(FireStrategyType type) {
        FireStrategy fireStrategy;
        switch (type) {
            case DEFAULT -> fireStrategy = new DefaultFireStrategy();
            case AROUND -> fireStrategy = new AroundFireStrategy();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return fireStrategy;
    }
}
