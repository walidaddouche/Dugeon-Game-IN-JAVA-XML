package Controller;

import views.state.GamePanel;
import views.state.Menu;

import javax.swing.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Game");
        GamePanel game = new GamePanel();
        game.runWindow(window);


    }
}


