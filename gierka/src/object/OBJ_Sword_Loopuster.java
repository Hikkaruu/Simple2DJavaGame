package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Loopuster extends Entity {

    public OBJ_Sword_Loopuster(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Loopuster";
        down1 = setup("objects/loopuster", gp.tileSize, gp.tileSize);
        attackValue = 5;
        description = "[" + name + "]\n" +
                "Through the writing from\nthe frightened spirit engraved\non this sword, you can feel\nits presence with you.\nAttack Value: 5";
        attackArea.width = 64;
        attackArea.height = 48;
        price = 120;
    }

}
