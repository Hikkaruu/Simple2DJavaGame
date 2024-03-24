package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow_Blue extends Entity {

    public OBJ_Bow_Blue(GamePanel gp) {
        super(gp);

        type = type_bow;
        name = "Blue Bow";
        down1 = setup("objects/Bow_Blue", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nWooden bow tinted blue.";
        attackValue = 1;
        price = 35;
    }
}
        