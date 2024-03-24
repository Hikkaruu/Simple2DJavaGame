package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {

        super(gp);

        name = "Chest";
        down1 = setup("objects/chest", gp.tileSize, gp.tileSize);
        collision = true;
    }

}
