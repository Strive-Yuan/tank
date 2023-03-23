package com.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BulletPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int BULLET_SIZE = 10;
    private static final int BULLET_SPEED = 5;

    private int bulletX = WIDTH / 2;
    private int bulletY = HEIGHT - BULLET_SIZE;

    private Timer timer;

    public BulletPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        timer = new Timer(50, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        bulletY -= BULLET_SPEED;

        if (bulletY < 0) {
            bulletY = HEIGHT - BULLET_SIZE;
        }

        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.RED);
        g.fillOval(bulletX, bulletY, BULLET_SIZE, BULLET_SIZE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bullet Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BulletPanel panel = new BulletPanel();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
