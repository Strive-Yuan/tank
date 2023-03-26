package com.normal.chain;

import com.normal.GameObject;
import com.normal.Group;
import com.normal.TankJPanel;
import com.normal.explode.FireExplode;
import com.normal.tank.Tank;
import com.normal.weapon.Bullet;

public class BulletAndTankCollider implements Collider {

    @Override
    public boolean checkCollisionDetection(GameObject go1, GameObject go2) {
        if (go1 instanceof Bullet bullet && go2 instanceof Tank tank) {
            if (Group.BAD.equals(bullet.group)) {
                return true;
            }
            if (tank.group.equals(Group.GOOD)) {
                return true;
            }
            if (bullet.rect.intersects(tank.rect) || tank.rect.intersects(bullet.rect)) {
                bullet.die();
                tank.die();
                TankJPanel.getInstance().gameObjectList.add(new FireExplode(bullet.x, bullet.y));
                return false;
            }
        } else if (go1 instanceof Tank && go2 instanceof Bullet) {
            return checkCollisionDetection(go2, go1);
        }
        return true;
    }
}
