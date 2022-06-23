package Model.Entity;

import Controller.KeyHandler;
import views.state.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX ;
    public final int screenY ;
    public int hasKey = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.screenY = gamePanel.screenHeight /2 - (gamePanel.tileSize/2);
        this.screenX = gamePanel.screenWidth /2 - (gamePanel.tileSize/2);
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        this.worldX = gamePanel.tileSize *15;
        this.worldY = gamePanel.tileSize *8;
        this.speed = 4;
        this.direction = "down";
        this.vitality = 100;
    }

    public void getPlayerImage() {
        try {

            up1= ImageIO.read(new FileInputStream("resources\\Tiles\\boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("resources\\Tiles\\boy_up_2.png"));
            down1 = ImageIO.read((new FileInputStream("resources\\Tiles\\boy_down_1.png")));
            down2 = ImageIO.read(new FileInputStream("resources\\Tiles\\boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("resources\\Tiles\\boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("resources\\Tiles\\boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("resources\\Tiles\\boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("resources\\Tiles\\boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.UpPressed || keyHandler.LeftPressed || keyHandler.DownPressed || keyHandler.RightPressed) {
            if (keyHandler.UpPressed) {
                direction = "up";
            } else if (keyHandler.DownPressed) {
                direction = "down";
            } else if (keyHandler.RightPressed) {
                direction = "right";
            } else if (keyHandler.LeftPressed) {
                direction = "left";
            }
            spriteCounter++;
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            int objIndex =  gamePanel.collisionChecker.checkObject(this,true);
            pickUpObject(objIndex);
            if(!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "right" -> worldX += speed;
                    case "left" -> worldX -= speed;
                }

            }
            if (spriteCounter > 5) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.objects[index].name;
            switch (objectName){
                case "KEY"->{
                    hasKey++;
                    gamePanel.objects[index] = null;
                    gamePanel.playSE(1);
                    gamePanel.uiGame.showMessage("You got a Key ");
                }
                case "DOOR"->{
                    if(hasKey > 0){
                        gamePanel.playSE(3);
                        hasKey--;
                        gamePanel.objects[index] = null;
                        gamePanel.uiGame.showMessage("You opened the door!");
                    }
                    else {
                        gamePanel.uiGame.showMessage("You dont have the key to open this door!");
                        gamePanel.uiGame.showMessage("You need to find the key to unlock the next level ");

                    }
                }
                case "BOOTS"->{
                        speed++;
                        gamePanel.objects[index] = null;
                        gamePanel.playSE(2);
                        gamePanel.uiGame.showMessage("Speed ++ ");
                }
            }
        }
    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public static void main(String[] args) throws IOException {
        BufferedImage Player_Positions = ImageIO.read(new File("resources\\Tiles\\preview.png"));
        for (int i = 0; i < Player_Positions.getWidth(); i+=16) {
            ImageIO.write(Player_Positions.getSubimage(i, 16, 16, 16), "png", new File("resources\\Tiles\\player"+i+".png"));
        }


    }
}
