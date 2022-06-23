package Model.Objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Object_Door extends SuperObject{
    public Object_Door() {
        name = "DOOR";
        try {
            image = ImageIO.read(new File("resources/Objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
