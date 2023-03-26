package com.normal;

import com.normal.tank.PlayerTank;
import com.normal.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TankJPanel extends JPanel {
    private static final TankJPanel INSTANCE = new TankJPanel(800, 600);
    public Integer width;
    public Integer height;
    public GameModel gameModel;


    public static TankJPanel getInstance() {
        return INSTANCE;
    }

    private TankJPanel(Integer width, Integer height) {
        setOpaque(true);
        setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.width = width;
        this.height = height;
        gameModel = new GameModel(new TankLister());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameModel.paintComponent(g);
    }

    class TankLister implements ActionListener {
        public Tank tank;

        public TankLister() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tank.move();
            repaint();
        }

        public void setTank(PlayerTank tank) {
            this.tank = tank;
        }
    }
}
