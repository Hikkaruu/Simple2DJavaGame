package entity;

import main.GamePanel;
import object.*;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 0;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage(){

        up1 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        up2 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        up3 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        down1 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        down2 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        down3 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        left1 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        left2 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        left3 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        right1 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        right2 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);
        right3 = setup("npc/merchant_down_3", gp.tileSize, gp.tileSize);

    }

    public void setDialogue(){

        dialogues[0] = "Welcome traveler.\nI have some good stuff.\nDo you want to trade?";
    }

    public void setItems() {

        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Potion_Blue(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Arrow_Normal(gp));
        inventory.add(new OBJ_Arrow_Night(gp));
        inventory.add(new OBJ_Bow_Blue(gp));
        inventory.add(new OBJ_Sword_Loopuster(gp));
        inventory.add(new OBJ_Sword_Bellial(gp));
        inventory.add(new OBJ_Bow_Phoenix(gp));

    }

    public void speak() {

        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }

}
