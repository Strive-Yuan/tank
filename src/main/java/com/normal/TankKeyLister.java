package com.normal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankKeyLister implements KeyListener {
    public Tank tank;

    public TankKeyLister(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        tank.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        tank.keyReleased(e);
    }
}
