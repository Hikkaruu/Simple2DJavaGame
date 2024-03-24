package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Bellial extends Entity {

    public OBJ_Sword_Bellial(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Bellial's Sword";
        down1 = setup("objects/belial", gp.tileSize, gp.tileSize);
        attackValue = 10;
        description = "[" + name + "]\nBelial's sword containing the power of evil.\nAttack Value: 10";
        attackArea.width = 64;
        attackArea.height = 48;
        price = 500;
    }

}
