package Model.Objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Object_Key extends SuperObject {
    public Object_Key() {
        name = "KEY";
        try {
            image = ImageIO.read(new File("resources/Objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    collision = true;
    }




}