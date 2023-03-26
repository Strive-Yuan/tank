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

    public static FireStrategy getFireStrategy(String type) {
        FireStrategyType fireType = FireStrategyType.getFireStrategyType(type);
        if (fireType == null) {
            throw new RuntimeException("开火策略配置无效！");
        }
        return FireStrategyStore.getFireStrategy(fireType);
    }
}
