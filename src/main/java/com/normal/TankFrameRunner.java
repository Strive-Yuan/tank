package com.normal;

public class TankFrameRunner {

    public static void main(String[] args) {
        TankFrame frame = new TankFrame();
        TankJPanel tankJPanel = TankJPanel.getInstance();
        frame.add(tankJPanel);
        frame.addKeyListener(tankJPanel.gameModel.tankKeyLister);
        frame.pack();
        frame.setVisible(true);
    }
}
