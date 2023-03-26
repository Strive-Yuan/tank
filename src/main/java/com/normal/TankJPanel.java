package com.normal;

import com.normal.FireStrategy.FireStrategyStore;
import com.normal.explode.Explode;
import com.normal.tank.EnemyTank;
import com.normal.tank.PlayerTank;
import com.normal.tank.Tank;
import com.normal.utils.ResourceConf;
import com.normal.weapon.Bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TankJPanel extends JPanel {
    private static final TankJPanel INSTANCE = new TankJPanel(800, 600);
    public Integer width;
    public Integer height;
    public Tank playerTank;
    public java.util.List<Tank> enemyTanks;
    public Timer timer;
    public TankKeyLister tankKeyLister;
    public java.util.List<Bullet> bullets;
    public java.util.List<Explode> explodes;


    public static TankJPanel getInstance() {
        return INSTANCE;
    }

    private TankJPanel(Integer width, Integer height) {
        setOpaque(true);
        setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;
        this.setBackground(Color.black);
        initTankConf();
        initLister();
        initFireStrategy();
    }

    private void initFireStrategy() {
        String playerAround = (String) ResourceConf.props.get("player_fire_strategy");
        this.playerTank.fireStrategy = FireStrategyStore.getFireStrategy(playerAround);

        String enemyAround = (String) ResourceConf.props.get("enemy_fire_strategy");
        for (Tank enemyTank : enemyTanks) {
            enemyTank.fireStrategy = FireStrategyStore.getFireStrategy(enemyAround);
        }
    }

    private void initLister() {
        this.tankKeyLister = new TankKeyLister(playerTank);
        timer = new Timer(25, new TankLister());
        timer.start();
    }

    private void initTankConf() {
        this.enemyTanks = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.explodes = new ArrayList<>();
        this.playerTank = new PlayerTank(100, 100);
        String enemyNum = (String) ResourceConf.props.get("enemy_num");
        for (int i = 0; i < Integer.parseInt(enemyNum); i++) {
            enemyTanks.add(new EnemyTank(200 + 50 * i, 200));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("发射子弹数量：:" + bullets.size(), 10, 20);
        g.drawString("地方坦克数量：:" + enemyTanks.size(), 10, 40);
        try {
            if (playerTank.living) {
                playerTank.paintComponent(g);
            }

            for (int i = 0; i < enemyTanks.size(); i++) {
                Tank enemy = enemyTanks.get(i);
                if (!enemy.living) {
                    enemyTanks.remove(enemy);
                } else {
                    enemy.paintComponent(g);
                    enemy.move();
                }
            }

            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                if (!bullet.living) {
                    bullets.remove(bullet);
                } else {
                    bullet.paintComponent(g);
                    bullet.collisionDetection();
                }
            }
            for (int i = 0; i < explodes.size(); i++) {
                Explode explode = explodes.get(i);
                explode.paintComponent(g);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class TankLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            playerTank.move();
            repaint();
        }
    }
}
