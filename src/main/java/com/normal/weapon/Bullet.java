package com.normal.weapon;

import com.normal.DirType;
import com.normal.GameObject;
import com.normal.Group;
import com.normal.utils.ResourceMgr;
import com.normal.TankJPanel;

import java.awt.*;

public class Bullet implements GameObject {
    public int x;
    public int y;
    public int width;
    public int height;
    public DirType dirType;
    public Group group;
    public int speed = 6;
    public boolean living = true;
    public Rectangle rect;

    public Bullet(int x, int y, DirType dirType, Group group) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.bulletL.getWidth();
        this.height = ResourceMgr.bulletL.getHeight();
        this.group = group;
        this.dirType = dirType;
        rect = new Rectangle(x, y, width, height);
    }

    public void paintComponent(Graphics g) {
        switch (dirType) {
            case L -> g.drawImage(ResourceMgr.bulletL, x -= speed, y, null);
            case R -> g.drawImage(ResourceMgr.bulletR, x += speed, y, null);
            case U -> g.drawImage(ResourceMgr.bulletU, x, y -= speed, null);
            case D -> g.drawImage(ResourceMgr.bulletD, x, y += speed, null);
        }
        this.rect.x = x;
        this.rect.y = y;
    }


    @Override
    public boolean isLiving() {
        TankJPanel panel = TankJPanel.getInstance();
        if (x < 0 || y < 30 || x > panel.width || y > panel.height) {
            this.living = false;
        }
        return this.living;
    }

    public void die() {
        this.living = false;
    }
}
