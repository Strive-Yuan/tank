package com.api;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tank {
    public int x;
    public int y;
    public DirType dirType = DirType.STOP;
    public boolean selectL;
    public boolean selectR;
    public boolean selectU;
    public boolean selectD;

    public int speed;


    public Tank() {
        this.x = 100;
        this.y = 100;
        this.speed = 5;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 5;
    }

    public void paintComponent(Graphics g) {
        g.fillRect(x, y, 50, 50);
        URL url = getClass().getResource("/image/tank.png");
        assert url != null;
        ImageIcon imageIcon = new ImageIcon(url);
        Image scaledImage = getScaledImage(imageIcon.getImage(), 40, 50);
        g.drawImage(scaledImage, 200, 50, 100, 100, null);
    }

    private Image getScaledImage(Image image, int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_FAST);
    }

    public void move(int num) {
        switch (dirType) {
            case L -> {
                if (selectL) {
                    x -= num;
                }
            }
            case R -> {
                if (selectR) {
                    x += num;
                }
            }
            case U -> {
                if (selectU) {
                    y -= num;
                }
            }
            case D -> {
                if (selectD) {
                    y += num;
                }
            }
        }
    }
}
