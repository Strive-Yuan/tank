package com.api;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankKeyLister implements KeyListener {
    private Tank tank;

    public TankKeyLister(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            tank.dirType = DirType.L;
            tank.selectL = true;
        }
        if (e.getKeyCode() == 38) {
            tank.dirType = DirType.U;
            tank.selectU = true;
        }
        if (e.getKeyCode() == 39) {
            tank.dirType = DirType.R;
            tank.selectR = true;
        }
        if (e.getKeyCode() == 40) {
            tank.dirType = DirType.D;
            tank.selectD = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            tank.selectL = false;
        }
        if (e.getKeyCode() == 38) {
            tank.selectU = false;
        }
        if (e.getKeyCode() == 39) {
            tank.selectR = false;
        }
        if (e.getKeyCode() == 40) {
            tank.selectD = false;
        }
    }
}
