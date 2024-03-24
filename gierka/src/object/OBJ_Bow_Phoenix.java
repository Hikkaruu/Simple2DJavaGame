package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow_Phoenix extends Entity {

    public OBJ_Bow_Phoenix(GamePanel gp) {
        super(gp);

        type = type_bow;
        name = "Phoenix Wing";
        down1 = setup("objects/phoenix_bow", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA bow that uses the fiery heat of the phoenix.";
        attackValue = 5;
        price = 220;
    }
}
