package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle(0,0,48,62);


        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("npc/oldman_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("npc/oldman_up_3", gp.tileSize, gp.tileSize);
        down1 = setup("npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("npc/oldman_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("npc/oldman_down_3", gp.tileSize, gp.tileSize);
        left1 = setup("npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("npc/oldman_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("npc/oldman_left_3", gp.tileSize, gp.tileSize);
        right1 = setup("npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("npc/oldman_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("npc/oldman_right_3", gp.tileSize, gp.tileSize);

    }

    public void setDialogue(){

        dialogues[0] = "Hurry!";
        dialogues[1] = "You have to get a key\nto open teleporting crystal room.";
        dialogues[2] = "The key is carried by Goblin King\nhis hideout is somewhere between trees\nin the upper right corner of island.";
        dialogues[4] = "The crystal will teleport you to the skeleton king dungeon.\nYou need to stop him!";
        dialogues[5] = "I would do this but my back hurts... Good luck!";
    }

    public void setAction(){

        actionLockCounter ++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25){
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

                actionLockCounter = 0;
            }

    }

    public void speak() {

        super.speak();

    }

}
