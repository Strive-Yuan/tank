package com.normal.explode;

import com.normal.GameObject;

import java.awt.*;

public abstract class Explode implements GameObject {
    int x;
    int y;
    int steep;
    boolean living = true;

    public abstract void paintComponent(Graphics g);

}
