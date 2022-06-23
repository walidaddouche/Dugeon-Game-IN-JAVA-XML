package Model.Tiles;

import Model.XMLReader.ReadXmlDomParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


public class ImageToMap {
    public static final int PixelSize = 16;
    public Path path;
    public Path pathXML;
    public ArrayList<ImageTuile> bufferedImageArrayList = new ArrayList<>();

    public ImageToMap(Path path,Path pathXML) {
        this.path = path;
        this.pathXML =pathXML;
        this.refactorList();
    }
    public ArrayList<ImageTuile> bufferedImageArrayList(String path) {
        ArrayList<ImageTuile> bufferedImages = new ArrayList<>();
        int i = 0;
        try {
            BufferedImage buffer = ImageIO.read(new File(String.valueOf(Path.of(path))));
            for (int y = 0; y < buffer.getHeight(); y += PixelSize) {
                for (int x = 0; x < buffer.getWidth(); x += PixelSize) {
                    BufferedImage dest = cropImage(buffer, x, y, PixelSize, PixelSize);
                    bufferedImages.add(new ImageTuile(dest, Integer.parseInt((Objects.requireNonNull(ReadXmlDomParser.getMapData(String.valueOf(pathXML))))[i])));
                    i++;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImages;
    }

    public ArrayList<ImageTuile> bufferedImageArrayList() {
        return bufferedImageArrayList("resources\\maps\\map.png");
    }


    public void refactorList() {
        for (ImageTuile image1 : this.bufferedImageArrayList()) {
            if (!bufferedImageArrayList.contains(image1)) {
                this.bufferedImageArrayList.add(image1);
            }
        }

    }


    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
        return bufferedImage.getSubimage(x, y, width, height);
    }






}



