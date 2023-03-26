package com.normal.utils;

import java.io.IOException;
import java.util.Properties;

public class ResourceConf {
    public static final Properties props = new Properties();

    static {
        try {
            props.load(ResourceConf.class.getResourceAsStream("/tankConf.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
