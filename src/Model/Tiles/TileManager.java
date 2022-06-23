package Model.Tiles;

import Model.XMLReader.ReadXmlDomParser;
import views.state.GamePanel;

import java.awt.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    ImageToMap imageToMap;
    int[][] mapTileNum;
    Path pathMapImage;
    Path pathMapXMl;

    public TileManager(GamePanel gp,String pathMapImage,String pathMapXml) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        tiles = new Tile[10];
        this.imageToMap = new ImageToMap(Paths.get(pathMapImage),Paths.get(pathMapXml));
        getTileImage();
        loadMap();
    }
    public TileManager(GamePanel gp) {
        // CONSTRUCTOR OF THE FIRST MAP
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        tiles = new Tile[10];
        this.pathMapXMl = Path.of("resources\\maps\\map.tmx");
        this.imageToMap = new ImageToMap(Paths.get("resources\\Tiles\\map.png"),
                this.pathMapXMl = Path.of("resources\\maps\\map.tmx"));;
        this.pathMapImage = imageToMap.path;

        getTileImage();
        loadMap();
        System.out.println(pathMapImage);
    }

    public void getTileImage() {
        for (ImageTuile tile : imageToMap.bufferedImageArrayList) {
            if (tile.id == 18) {
                tiles[ReadXmlDomParser.getMapDataID(String.valueOf(pathMapXMl)).get(tile.id)] = new Tile(tile.bufferedImage, tile.id, true);

            } else {
                tiles[ReadXmlDomParser.getMapDataID(String.valueOf(pathMapXMl)).get(tile.id)] = new Tile(tile.bufferedImage, tile.id,false);
            }

        }

    }

    public void loadMap() {
        HashMap<Integer, Integer> res = ReadXmlDomParser.getMapDataID(String.valueOf(pathMapXMl));
        int col = 0;
        int row = 0;
        int done = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            String[] indexs = Arrays.copyOfRange(Objects.requireNonNull(ReadXmlDomParser.getMapData(String.valueOf(pathMapXMl))), done, 20 + done);
            while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(indexs[col]);
                    int target = res.get(num);
                    mapTileNum[col][row] = target;
                    col++;
            }
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                done += 20;
            }


        }

    }

    public void draw(Graphics2D g2) {





        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }



}