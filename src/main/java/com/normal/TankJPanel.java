package com.normal;

import com.normal.FireStrategy.FireStrategyStore;
import com.normal.chain.BulletAndTankCollider;
import com.normal.chain.ColliderChain;
import com.normal.tank.EnemyTank;
import com.normal.tank.PlayerTank;
import com.normal.tank.Tank;
import com.normal.utils.ResourceConf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TankJPanel extends JPanel {
    private static final TankJPanel INSTANCE = new TankJPanel(800, 600);
    public Integer width;
    public Integer height;
    public Timer timer;
    public TankKeyLister tankKeyLister;
    public java.util.List<GameObject> gameObjectList;
    public ColliderChain colliderChain;


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
        initCollider();
    }

    private void initCollider() {
        this.colliderChain = new ColliderChain();
        this.colliderChain.addCollider(new BulletAndTankCollider());
    }

    private void initTankConf() {
        this.gameObjectList = new ArrayList<>();
        String playerAround = (String) ResourceConf.props.get("player_fire_strategy");
        PlayerTank playerTank = new PlayerTank(100, 100, FireStrategyStore.getFireStrategy(playerAround));
        this.tankKeyLister = new TankKeyLister(playerTank);
        timer = new Timer(25, new TankLister(playerTank));
        timer.start();
        this.gameObjectList.add(playerTank);

        String enemyNum = (String) ResourceConf.props.get("enemy_num");
        String enemyAround = (String) ResourceConf.props.get("enemy_fire_strategy");
        for (int i = 0; i < Integer.parseInt(enemyNum); i++) {
            this.gameObjectList.add(new EnemyTank(200 + 50 * i, 200, FireStrategyStore.getFireStrategy(enemyAround)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("游戏物体数量：:" + gameObjectList.size(), 10, 20);
        BulletAndTankCollider bulletAndTankCollider = new BulletAndTankCollider();
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject go1 = gameObjectList.get(i);
            if (!go1.isLiving()) {
                gameObjectList.remove(i);
                return;
            } else {
                go1.paintComponent(g);
            }

            for (int j = 0; j < gameObjectList.size(); j++) {
                GameObject go2 = gameObjectList.get(j);
                bulletAndTankCollider.checkCollisionDetection(go1, go2);
            }
        }
    }

    class TankLister implements ActionListener {
        public Tank tank;

        public TankLister(Tank tank) {
            this.tank = tank;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tank.move();
            repaint();
        }
    }
}
