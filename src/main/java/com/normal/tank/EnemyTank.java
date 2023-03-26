package com.normal.tank;

import com.normal.*;
import com.normal.FireStrategy.FireStrategy;
import com.normal.utils.ResourceMgr;
import com.normal.weapon.Bullet;

import java.awt.*;

public class EnemyTank extends Tank {

    public EnemyTank(int x, int y, FireStrategy fireStrategy) {
        this.x = x;
        this.y = y;
        this.oldY = y;
        this.oldX = x;
        this.speed = 3;
        this.group = Group.BAD;
        this.dirType = DirType.U;
        this.fireStrategy = fireStrategy;
        image = ResourceMgr.enemyImageMap.get(DirType.U);
    }

    public void addBullet(Bullet bullet) {
        TankJPanel.getInstance().bullets.add(bullet);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, x, y, 80, 100, null);
    }

    public void move() {
        oldY = y;
        oldX = x;
        switch (dirType) {
            case L -> x -= speed;
            case R -> x += speed;
            case U -> y -= speed;
            case D -> y += speed;
        }
        boundaryCheck();
        if (random.nextInt(100) > 95) {
            dirType = DirType.randomDir();
            image = ResourceMgr.enemyImageMap.get(dirType);
            fireStrategy.fire(this);
        }
    }

    @Override
    public void die() {
        this.living = false;
    }
}
