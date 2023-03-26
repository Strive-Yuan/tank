package com.normal;

import com.normal.tank.PlayerTank;
import com.normal.tank.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankKeyLister implements KeyListener {
    public PlayerTank tank;

    public TankKeyLister(Tank tank) {
        this.tank = (PlayerTank) tank;
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
