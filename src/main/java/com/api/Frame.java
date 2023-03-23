package com.api;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Frame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("tank war");
        // 设置窗口大小
        frame.setSize(700, 600);
        // 设置窗口关闭操作，退出应用程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(Frame.class.getClassLoader().getResource("image/tank.png")));
        Image scaledInstance = imageIcon.getImage().getScaledInstance(40, 50, Image.SCALE_FAST);
        imageIcon = new ImageIcon(scaledInstance);
        JLabel label = new JLabel(imageIcon);
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}
