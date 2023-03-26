package com.normal;

import com.normal.FireStrategy.FireStrategyStore;
import com.normal.chain.BulletAndTankCollider;
import com.normal.chain.ColliderChain;
import com.normal.tank.EnemyTank;
import com.normal.tank.PlayerTank;
import com.normal.utils.ResourceConf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameModel {
    public Timer timer;
    public TankKeyLister tankKeyLister;
    public java.util.List<GameObject> gameObjectList;
    public ColliderChain colliderChain;

    public GameModel(TankJPanel.TankLister tankLister) {
        initTankConf(tankLister);
        initCollider();
    }

    private void initCollider() {
        this.colliderChain = new ColliderChain();
        this.colliderChain.addAllCollider(java.util.List.of(new BulletAndTankCollider()));
    }

    private void initTankConf(TankJPanel.TankLister tankLister) {
        this.gameObjectList = new ArrayList<>();
        String playerAround = (String) ResourceConf.props.get("player_fire_strategy");
        PlayerTank playerTank = new PlayerTank(100, 100, FireStrategyStore.getFireStrategy(playerAround));
        this.tankKeyLister = new TankKeyLister(playerTank);
        //坦克监听实现不是很合理后续有时间优化 todo
        tankLister.setTank(playerTank);
        timer = new Timer(25, tankLister);
        timer.start();
        this.gameObjectList.add(playerTank);

        String enemyNum = (String) ResourceConf.props.get("enemy_num");
        String enemyAround = (String) ResourceConf.props.get("enemy_fire_strategy");
        for (int i = 0; i < Integer.parseInt(enemyNum); i++) {
            this.gameObjectList.add(new EnemyTank(200 + 50 * i, 200, FireStrategyStore.getFireStrategy(enemyAround)));
        }
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("游戏物体数量：:" + gameObjectList.size(), 10, 20);
        BulletAndTankCollider bulletAndTankCollider = new BulletAndTankCollider();
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject go1 = gameObjectList.get(i);
            if (!go1.isLiving()) {
                gameObjectList.remove(i);
            }
        }
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject go1 = gameObjectList.get(i);
            if (go1.isLiving()) {
                go1.paintComponent(g);
            }
            for (int j = 0; j < gameObjectList.size(); j++) {
                GameObject go2 = gameObjectList.get(j);
                bulletAndTankCollider.checkCollisionDetection(go1, go2);
            }
        }
    }
}