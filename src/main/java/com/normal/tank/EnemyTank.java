package com.normal.tank;

import com.normal.*;
import com.normal.FireStrategy.FireStrategy;
import com.normal.utils.ResourceConf;
import com.normal.utils.ResourceMgr;
import com.normal.weapon.Bullet;

import java.awt.*;

public class EnemyTank extends Tank {
    public EnemyTank(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 100;
        this.oldY = y;
        this.oldX = x;
        this.speed = Integer.parseInt((String) ResourceConf.props.get("enemy_speed"));
        this.group = Group.BAD;
        this.dirType = DirType.U;
        image = ResourceMgr.enemyImageMap.get(DirType.U);
        rect = new Rectangle(x, y, this.width, this.height);
    }

    public EnemyTank(int x, int y, FireStrategy fireStrategy) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 100;
        this.oldY = y;
        this.oldX = x;
        this.speed = Integer.parseInt((String) ResourceConf.props.get("enemy_speed"));
        this.group = Group.BAD;
        this.dirType = DirType.U;
        this.fireStrategy = fireStrategy;
        image = ResourceMgr.enemyImageMap.get(DirType.U);
        rect = new Rectangle(x, y, this.width, this.height);
    }

    public void addBullet(Bullet bullet) {
        TankJPanel.getInstance().gameObjectList.add(bullet);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        move();
    }

    @Override
    public boolean isLiving() {
        return this.living;
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
        this.rect.x = x;
        this.rect.y = y;
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
