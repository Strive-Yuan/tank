package com.api;

import java.awt.*;

public class TankFrameRunner {

    public static void main(String[] args) {
        TankFrame frame = new TankFrame();
        TankJPanel tankJPanel = new TankJPanel();
        frame.add(tankJPanel);
        frame.addKeyListener(tankJPanel.tankKeyLister);
        frame.pack();
        frame.setVisible(true);
    }
}
