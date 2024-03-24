package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Red Potion]\nRestore your life by " + value + ".";
        price = 6;
    }
    public void use(Entity entity) {


        gp.gameState = gp.dialogueState;
        if (gp.player.life == gp.player.maxLife){

            gp.player.canUse = false;
            gp.ui.currentDialogue = "Your health is full!";
        }
        else {

            gp.player.canUse = true;
            gp.ui.currentDialogue = "You drink the " + name + "!\n"
                    + "Your life has been restored by " + value + ".";
            entity.life += value;
            if (gp.player.life > gp.player.maxLife) {

                gp.player.life = gp.player.maxLife;
            }
            gp.playSE(2);
        }
    }

}
