package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Skeleton_Head extends Entity {

    GamePanel gp;
    public OBJ_Skeleton_Head(GamePanel gp) {

        super(gp);

        name = "Skeleton Head";
        down1 = setup("objects/skeleton_head", gp.tileSize, gp.tileSize);

    }

}
