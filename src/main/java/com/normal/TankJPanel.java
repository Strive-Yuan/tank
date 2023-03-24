package com.normal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TankJPanel extends JPanel {
    private static final TankJPanel INSTANCE = new TankJPanel();
    public Tank tank;
    public Tank enemy;
    public Timer timer;
    public TankKeyLister tankKeyLister;
    public java.util.List<Bullet> bullets;


    public static TankJPanel getInstance() {
        return INSTANCE;
    }

    private TankJPanel() {
        setOpaque(true);
        setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.black);
        this.tank = new Tank(100, 100, Group.GOOD);
        this.enemy = new Tank(200, 200, Group.BAD);
        this.tankKeyLister = new TankKeyLister(tank);
        this.bullets = new ArrayList<>();
        timer = new Timer(25, new TankLister(3));
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            tank.paintComponent(g);
            enemy.paintComponent(g);
            for (Bullet bullet : bullets) {
                bullet.paintComponent(g);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
