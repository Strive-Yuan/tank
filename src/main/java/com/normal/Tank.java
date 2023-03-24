package com.normal;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank {
    public int x;
    public int y;
    public DirType dirType = DirType.D;
    public boolean selectL;
    public boolean selectR;
    public boolean selectU;
    public boolean selectD;
    public int speed;
    public BufferedImage image;
    public Group group;

    public Tank(int x, int y, Group group) {
        this.x = x;
        this.y = y;
        this.speed = 5;
        if (Group.GOOD.equals(group)) {
            image = ResourceMgr.goodTankD;
        }
        if (Group.BAD.equals(group)) {
            image = ResourceMgr.badTankU;
        }
        this.group = group;
    }

    public void addBullet(Bullet bullet) {
        TankJPanel.getInstance().bullets.add(bullet);
    }

    public void paintComponent(Graphics g) throws IOException {

        if (Group.GOOD.equals(group)) {
            g.drawImage(image, x, y, 80, 100, null);
        }
        if (Group.BAD.equals(group)) {
            g.drawImage(image, x, y, 80, 100, null);
        }
    }

    public void fire() {
        switch (dirType) {
            case L, R -> this.addBullet(new Bullet(x + 25, y + 35, dirType, Group.GOOD));
            case U, D -> this.addBullet(new Bullet(x + 25, y + 30, dirType, Group.GOOD));
        }
    }

    public void move(int num) {
        switch (dirType) {
            case L -> {
                if (selectL) {
                    x -= num;
                }
            }
            case R -> {
                if (selectR) {
                    x += num;
                }
            }
            case U -> {
                if (selectU) {
                    y -= num;
                }
            }
            case D -> {
                if (selectD) {
                    y += num;
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (Group.GOOD.equals(group)) {
            switch (key) {
                case KeyEvent.VK_LEFT -> {
                    dirType = DirType.L;
                    image = ResourceMgr.goodTankL;
                    selectL = true;
                }
                case KeyEvent.VK_RIGHT -> {
                    dirType = DirType.R;
                    image = ResourceMgr.goodTankR;
                    selectR = true;
                }
                case KeyEvent.VK_UP -> {
                    dirType = DirType.U;
                    image = ResourceMgr.goodTankU;
                    selectU = true;
                }
                case KeyEvent.VK_DOWN -> {
                    dirType = DirType.D;
                    image = ResourceMgr.goodTankD;
                    selectD = true;
                }
            }
        }
        if (Group.BAD.equals(group)) {
            switch (key) {
                case KeyEvent.VK_LEFT -> {
                    dirType = DirType.L;
                    image = ResourceMgr.badTankL;
                    selectL = true;
                }
                case KeyEvent.VK_RIGHT -> {
                    dirType = DirType.R;
                    image = ResourceMgr.badTankR;
                    selectR = true;
                }
                case KeyEvent.VK_UP -> {
                    dirType = DirType.U;
                    image = ResourceMgr.badTankU;
                    selectU = true;
                }
                case KeyEvent.VK_DOWN -> {
                    dirType = DirType.D;
                    image = ResourceMgr.badTankD;
                    selectD = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> selectL = false;
            case KeyEvent.VK_RIGHT -> selectR = false;
            case KeyEvent.VK_UP -> selectU = false;
            case KeyEvent.VK_DOWN -> selectD = false;
            case KeyEvent.VK_ENTER -> this.fire();
        }
    }
}
