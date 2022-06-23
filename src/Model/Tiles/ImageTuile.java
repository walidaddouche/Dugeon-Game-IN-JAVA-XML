package Model.Tiles;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageTuile {
    public BufferedImage bufferedImage;
    public int id;
    public boolean collision ;
    public ImageTuile(BufferedImage bufferedImage, int id) {
        this.bufferedImage = bufferedImage;
        this.id = id;
    }
    public ImageTuile(BufferedImage bufferedImage, int id,boolean collision) {
        this.bufferedImage = bufferedImage;
        this.id = id;
        this.collision = collision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageTuile)) return false;
        ImageTuile that = (ImageTuile) o;
        return areSame(this.bufferedImage, that.bufferedImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bufferedImage);
    }
    public static boolean areSame(BufferedImage img1, BufferedImage img2) {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();

        long diff = 0;
        for (int j = 0; j < h1; j++) {
            for (int i = 0; i < w1; i++) {
                //Getting the RGB values of a pixel
                int pixel1 = img1.getRGB(i, j);
                Color color1 = new Color(pixel1, true);
                int r1 = color1.getRed();
                int g1 = color1.getGreen();
                int b1 = color1.getBlue();
                int pixel2 = img2.getRGB(i, j);
                Color color2 = new Color(pixel2, true);
                int r2 = color2.getRed();
                int g2 = color2.getGreen();
                int b2 = color2.getBlue();
                //sum of differences of RGB values of the two images
                long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                diff = diff + data;
            }
        }

        return diff == 0;

    }

    @Override
    public String toString() {
        return "ImageTuile{" +
                "bufferedImage="+
                ", id=" + id +
                '}';
    }
}