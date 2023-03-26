package com.normal;

import com.normal.utils.ResourceConf;

public class TankFrameRunner {

    public static void main(String[] args) {
        System.out.println(ResourceConf.props.get("enemy_num"));
        TankFrame frame = new TankFrame();
        TankJPanel tankJPanel = TankJPanel.getInstance();
        frame.add(tankJPanel);
        frame.addKeyListener(tankJPanel.tankKeyLister);
        frame.pack();
        frame.setVisible(true);
    }
}
