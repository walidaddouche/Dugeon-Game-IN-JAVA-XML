package Model.Tiles;

import java.awt.image.BufferedImage;


public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public int id ;

    public Tile(BufferedImage image,int id,boolean collision) {
        this.image = image;
        this.id = id;
        this.collision = collision;
    }


}
