package Model.Objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Object_Boots extends SuperObject {
    public Object_Boots() {
        name = "BOOTS";
        try {
            image = ImageIO.read(new File("resources/Objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

}