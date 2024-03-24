package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Null extends Entity {

    public OBJ_Null(GamePanel gp) {
        super(gp);

        type = type_null;
        name = "No Shield";
        down1 = setup("objects/null", gp.tileSize, gp.tileSize);
        defenseValue = 0;
    }
}
        