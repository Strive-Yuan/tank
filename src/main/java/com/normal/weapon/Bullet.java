package com.normal.weapon;

import com.normal.DirType;
import com.normal.Group;
import com.normal.explode.FireExplode;
import com.normal.utils.ResourceMgr;
import com.normal.TankJPanel;
import com.normal.tank.Tank;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Bullet {
    public int x;
    public int y;
    public DirType dirType;
    public Group group;
    public int speed = 6;
    public boolean living = true;

    public Bullet(int x, int y, DirType dirType, Group group) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.dirType = dirType;
    }

    public void paintComponent(Graphics g) throws IOException {
        switch (dirType) {
            case L -> g.drawImage(ResourceMgr.bulletL, x -= speed, y, null);
            case R -> g.drawImage(ResourceMgr.bulletR, x += speed, y, null);
            case U -> g.drawImage(ResourceMgr.bulletU, x, y -= speed, null);
            case D -> g.drawImage(ResourceMgr.bulletD, x, y += speed, null);
        }
        isLiving();
    }

    private void isLiving() {
        TankJPanel panel = TankJPanel.getInstance();
        if (x < 0 || y < 30 || x > panel.width || y > panel.height) {
            this.living = false;
        }
    }

    public void collisionDetection() {
        TankJPanel jPanel = TankJPanel.getInstance();
        checkCollisionDetection(jPanel.enemyTanks);
//        checkCollisionDetection(List.of(jPanel.playerTank));

    }

    private void checkCollisionDetection(List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            if (!tank.living) {
                tanks.remove(tank);
            }
            if (group.equals(tank.group)) {
                return;
            }
            Rectangle tankRect = new Rectangle(tank.x, tank.y, tank.image.getWidth(), tank.image.getHeight());
            Rectangle bulletRect = new Rectangle(this.x, this.y, ResourceMgr.bulletL.getWidth(), ResourceMgr.bulletL.getHeight());
            if (tankRect.intersects(bulletRect) || bulletRect.intersects(tankRect)) {
                die();
                tank.die();
                TankJPanel.getInstance().explodes.add(new FireExplode(x, y));
            }
        }
    }

    public void die() {
        this.living = false;
    }
}
