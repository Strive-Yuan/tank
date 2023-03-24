package com.normal;

import java.awt.*;
import java.io.IOException;

public class Bullet {
    public int x;
    public int y;
    public DirType dirType;
    public Group group;
    public int speed = 6;

    public Bullet(int x, int y, DirType dirType,Group group) {
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

    }
}
