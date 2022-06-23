package Model.Objects;

import views.state.GamePanel;

public class AssetsSetter {
    GamePanel gp ;
    public AssetsSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.objects[0] = new Object_Key();
        gp.objects[0].worldX = 18 * gp.tileSize;
        gp.objects[0].worldY = 7 * gp.tileSize;


        gp.objects[1] = new Object_Door();
        gp.objects[1].worldX = 19 * gp.tileSize;
        gp.objects[1].worldY = 18 * gp.tileSize;




    }

}
