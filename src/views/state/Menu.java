package views.state;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel implements States {
    private final STATE state = STATE.MENU;
    public JButton START_BUTTON = new JButton();
    public JButton CREDITS_BUTTON = new JButton();
    public JButton EXIT_BUTTON = new JButton();
    public boolean START_BUTTON_PRESSED  = false;
    public boolean CREDITS_BUTTON_PRESSED  =false;
    public boolean EXIT_PRESSED  = false;
    public boolean PRESSED = false;

    public Menu() {
        paintComponents();
    }


    public void paintComponents() {


        START_BUTTON.setText("START GAME");
        START_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CREDITS_BUTTON.setText("CREDITS");
        CREDITS_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        EXIT_BUTTON.setText("EXIT");
        EXIT_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(EXIT_BUTTON, GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CREDITS_BUTTON, GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(START_BUTTON, GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(START_BUTTON, GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CREDITS_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EXIT_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(83, Short.MAX_VALUE))
        );
    }

    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            GamePanel gamePanel = new GamePanel();
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Dungeon Game");
            gamePanel.runWindow(window);
            START_BUTTON_PRESSED = true;
    }

    public void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CREDITS_BUTTON_PRESSED = true;
        PRESSED = true;

    }
    public void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        EXIT_PRESSED = true;
        PRESSED = true;

    }


    @Override
    public void run() {

    }

    @Override
    public void runWindow(Window window) {
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}