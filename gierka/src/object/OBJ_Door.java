package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("objects/grille_door", gp.tileSize, gp.tileSize);
        collision = true;
        mechObj = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 64;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
