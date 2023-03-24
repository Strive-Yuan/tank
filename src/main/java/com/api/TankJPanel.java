package com.api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TankJPanel extends JPanel {
    public Tank tank;
    public Timer timer;
    public TankKeyLister tankKeyLister;


    public TankJPanel() {
        setOpaque(true);
        setPreferredSize(new Dimension(800, 600));
        this.tank = new Tank();
        this.tankKeyLister = new TankKeyLister(tank);
        timer = new Timer(25, new TankLister(3));
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tank.paintComponent(g);
    }

    class TankLister implements ActionListener {
        int pixelNum;

        public TankLister(int pixelNum) {
            this.pixelNum = pixelNum;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tank.move(pixelNum);
            repaint();
        }
    }



}
