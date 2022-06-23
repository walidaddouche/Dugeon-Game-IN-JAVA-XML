package views.ui;

import Model.Objects.Object_Key;
import views.state.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UiGame {
    GamePanel gamePanel;
    Font arial_40 = new Font("ARIAL", Font.PLAIN, 40);
    Object_Key key = new Object_Key();
    BufferedImage KEY_IMAGE = key.image;
    BufferedImage HeartImage ;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UiGame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try{
             HeartImage = ImageIO.read(new File("resources/Objects/Sprite_heart.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
        graphics2D.drawImage(KEY_IMAGE, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawString("x " + gamePanel.player.hasKey, 74, 65);
        graphics2D.drawImage(HeartImage, 20, 100,  70,70,null);
        graphics2D.drawString(String.valueOf(gamePanel.player.vitality), 100, 145);

        if(messageOn){
            graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
            graphics2D.drawString(message, gamePanel.tileSize/2 ,gamePanel.tileSize*5 );
            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn  = false;
            }
        }
    }
}
