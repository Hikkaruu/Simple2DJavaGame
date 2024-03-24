package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Blue extends Entity {

    GamePanel gp;

    public OBJ_Potion_Blue(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Blue Potion";
        value = 6;
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Blue Potion]\nRestore your mana by " + value + ".";
        price = 8;
    }
    public void use(Entity entity) {


        gp.gameState = gp.dialogueState;
        if (gp.player.mana == gp.player.maxMana){

            gp.player.canUse = false;
            gp.ui.currentDialogue = "Your mana is full!";
        }
        else {

            gp.player.canUse = true;
            gp.ui.currentDialogue = "You drink the " + name + "!\n"
                    + "Your mana has been restored by " + value + ".";
            entity.mana += value;
            if (gp.player.mana > gp.player.maxMana) {

                gp.player.mana = gp.player.maxMana;
            }
            gp.playSE(2);
        }
    }

}
