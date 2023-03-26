package com.normal.chain;

import com.normal.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider {
    public List<Collider> colliders = new ArrayList<>();

    @Override
    public boolean checkCollisionDetection(GameObject go1, GameObject go2) {
        for (Collider c : colliders) {
            if (!c.checkCollisionDetection(go1, go2)) {
                return false;
            }
        }
        return true;
    }

    public void addCollider(Collider collider) {
        colliders.add(collider);
    }
}
