package Model.Objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Object_Chest extends SuperObject{
    public Object_Chest() {
        name = "CHEST";
        try {
            image = ImageIO.read(new File("resources/Objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();}
        collision = true;
    }

}
