package testClone.test;

import java.awt.*;
import java.awt.event.*;

public class EventExample extends Frame implements ActionListener {
    private TextField textField;
    private Button button;

    public EventExample() {
        setTitle("Event Example");
        setLayout(new FlowLayout());

        textField = new TextField(20);
        add(textField);

        button = new Button("Click me");
        add(button);

        // 为按钮添加ActionEvent监听器
        button.addActionListener(this);

        // 为窗口添加WindowEvent监听器
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    // 实现ActionEvent监听器接口的方法
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        System.out.println("Button clicked: " + text);
    }

    public static void main(String[] args) {
        EventExample example = new EventExample();
    }
}

