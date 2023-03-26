package com.normal.tank;

import com.normal.DirType;
import com.normal.FireStrategy.DefaultFireStrategy;
import com.normal.FireStrategy.FireStrategy;
import com.normal.GameObject;
import com.normal.Group;
import com.normal.TankJPanel;
import com.normal.weapon.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Tank implements GameObject {
    public int x;
    public int y;
    public int width;
    public int height;
    public int oldX;
    public int oldY;
    public DirType dirType = DirType.D;
    public boolean selectL;
    public boolean selectR;
    public boolean selectU;
    public boolean selectD;
    public int speed;
    public BufferedImage image;
    public boolean living = true;
    public Group group;
    public Random random = new Random();
    public FireStrategy fireStrategy = new DefaultFireStrategy();
    public Rectangle rect;

    public abstract void paintComponent(Graphics g);

    public abstract void addBullet(Bullet bullet);

    public abstract void move();

    public abstract void die();


    protected void boundaryCheck() {
        TankJPanel panel = TankJPanel.getInstance();
        if (x < 0 || y < 0 || x + 80 > panel.width || y + 100 > panel.height) {
            this.back();
        }
    }

    protected void back() {
        this.x = oldX;
        this.y = oldY;
    }
}
