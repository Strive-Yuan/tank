package com.normal.FireStrategy;

import com.normal.DirType;
import com.normal.tank.Tank;
import com.normal.weapon.Bullet;

public class AroundFireStrategy implements  FireStrategy{
    @Override
    public void fire(Tank tank) {
        tank.addBullet(new Bullet(tank.x + 25, tank.y + 35, DirType.L, tank.group));
        tank.addBullet(new Bullet(tank.x + 25, tank.y + 35, DirType.R, tank.group));
        tank.addBullet(new Bullet(tank.x + 25, tank.y + 35, DirType.U, tank.group));
        tank.addBullet(new Bullet(tank.x + 25, tank.y + 35, DirType.D, tank.group));
    }
}
