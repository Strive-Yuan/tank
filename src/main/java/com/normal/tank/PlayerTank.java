package com.normal.tank;

import com.normal.*;
import com.normal.FireStrategy.FireStrategy;
import com.normal.utils.Audio;
import com.normal.utils.ResourceConf;
import com.normal.utils.ResourceMgr;
import com.normal.weapon.Bullet;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerTank extends Tank {

    public PlayerTank(int x, int y) {
        this.x = x;
        this.y = y;
        this.oldY = y;
        this.oldX = x;
        this.speed = Integer.parseInt((String) ResourceConf.props.get("player_speed"));
        this.group = Group.GOOD;
        image = ResourceMgr.playerImageMap.get(DirType.D);
    }

    public PlayerTank(int x, int y, FireStrategy fireStrategy) {
        this.x = x;
        this.y = y;
        this.oldY = y;
        this.oldX = x;
        this.speed = Integer.parseInt((String) ResourceConf.props.get("player_speed"));
        this.group = Group.GOOD;
        image = ResourceMgr.playerImageMap.get(DirType.D);
        this.fireStrategy = fireStrategy;
    }

    public void addBullet(Bullet bullet) {
        TankJPanel.getInstance().bullets.add(bullet);
    }

    public void paintComponent(Graphics g) {
        if (!living) return;
        g.drawImage(image, x, y, 80, 100, null);
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> {
                dirType = DirType.L;
                image = ResourceMgr.playerImageMap.get(DirType.L);
                selectL = true;
            }
            case KeyEvent.VK_RIGHT -> {
                dirType = DirType.R;
                image = ResourceMgr.playerImageMap.get(DirType.R);
                selectR = true;
            }
            case KeyEvent.VK_UP -> {
                dirType = DirType.U;
                image = ResourceMgr.playerImageMap.get(DirType.U);
                selectU = true;
            }
            case KeyEvent.VK_DOWN -> {
                dirType = DirType.D;
                image = ResourceMgr.playerImageMap.get(DirType.D);
                selectD = true;
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
            case KeyEvent.VK_SPACE -> {
                fireStrategy.fire(this);
                new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
            }
        }
    }

    public void move() {
        oldY = y;
        oldX = x;
        switch (dirType) {
            case L -> {
                if (selectL) {
                    x -= speed;
                }
            }
            case R -> {
                if (selectR) {
                    x += speed;
                }
            }
            case U -> {
                if (selectU) {
                    y -= speed;
                }
            }
            case D -> {
                if (selectD) {
                    y += speed;
                }
            }
        }
        boundaryCheck();
    }

    @Override
    public void die() {
        this.living = false;
    }
}
