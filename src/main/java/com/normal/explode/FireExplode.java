package com.normal.explode;

import com.normal.TankJPanel;
import com.normal.utils.Audio;
import com.normal.utils.ResourceMgr;

import java.awt.*;

public class FireExplode extends Explode {

    public FireExplode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(ResourceMgr.explodes[steep++], x, y, 80, 100, null);
        if (steep > ResourceMgr.explodes.length - 1) {
            TankJPanel.getInstance().explodes.remove(this);
        }
    }
}
