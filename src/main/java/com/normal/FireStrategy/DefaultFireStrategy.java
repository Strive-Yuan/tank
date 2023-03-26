package com.normal.FireStrategy;

import com.normal.tank.Tank;
import com.normal.weapon.Bullet;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        switch (tank.dirType) {
            case L, R -> tank.addBullet(new Bullet(tank.x + 25, tank.y + 35, tank.dirType, tank.group));
            case U, D -> tank.addBullet(new Bullet(tank.x + 25, tank.y + 30, tank.dirType, tank.group));
        }
    }
}
