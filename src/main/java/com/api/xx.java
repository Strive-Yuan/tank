package com.api;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class xx {

    public static void main(String[] args) {
        JFrame frame = new JFrame("tank war");
        // 设置窗口大小
        frame.setSize(700, 600);
        // 设置窗口关闭操作，退出应用程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        URL url = xx.class.getResource("/image/tank.png");
        assert url != null;
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel label = new JLabel(imageIcon);
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}