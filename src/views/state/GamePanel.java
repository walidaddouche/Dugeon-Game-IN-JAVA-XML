package views.state;

import Controller.KeyHandler;
import Model.Objects.AssetsSetter;
import Model.Objects.SuperObject;
import Model.Sound.Sound;
import Model.Tiles.CollisionChecker;
import Model.Entity.Player;
import Model.Tiles.TileManager;
import views.ui.UiGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable , States {
    public final int OriginalTileSize = 16; // 16x16
    final int scale = 3;
    public STATE state = STATE.GAME;

    public  final int tileSize = OriginalTileSize * scale; // 48 x 48
    public  final int maxScreenCol = 25;
    public  final int maxScreenRow = 25;
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    int FPS = 60;


    Sound music = new Sound();
    Sound sound = new Sound();
    public TileManager tileManager = new TileManager(this);
    public Player player = new Player(this,keyHandler);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public SuperObject[] objects = new SuperObject[10];
    public AssetsSetter setterObj = new AssetsSetter(this);
    public UiGame uiGame = new UiGame(this);
    //WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;

    public GamePanel()  {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void SetUpGame(){
        playMusic(0);
        setterObj.setObject();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // UPDATE INFORMATION SUCH AS CHAR POSITION
            //  DRAW THE SCREEN WITH THE UPDATES INFORMATION
            update();
            repaint();
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime / 1000000;

            try {
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        player.update();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        //TILES
        tileManager.draw(graphics2D);
        for (SuperObject object : objects) {
            if( object != null) {
                object.draw(graphics2D, this);
                graphics2D.drawRect(object.worldX + object.solidAreaDefaultX ,object.worldY + object.solidAreaDefaultY,object.solidArea.width,object.solidArea.height);
            }
        }
        //PLAYER
        player.draw(graphics2D);

        uiGame.draw(graphics2D);
        graphics2D.dispose();

    }
    @Override
    public void runWindow(Window window){
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.SetUpGame();
        this.startGameThread();
    }

    @Override
    public void paintComponents() {

    }
}
