package com.normal.chain;

import com.normal.GameObject;

public interface Collider {
    boolean checkCollisionDetection(GameObject go1, GameObject go2);
}
