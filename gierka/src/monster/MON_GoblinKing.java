package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_GoblinKing extends Entity {

    GamePanel gp;
    int spawnX, spawnY;
    public MON_GoblinKing(GamePanel gp, int spawnX, int spawnY) {
        super(gp);

        this.gp = gp;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        type = type_monster;
        name = "Goblin King";
        speed = 2;
        maxLife = 60;
        life = maxLife;
        attack = 5;
        defense = 1;
        exp = 80;
        isBoss = true;

        solidArea.x = 45;
        solidArea.y = 30;
        solidArea.width = 54;
        solidArea.height = 54;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setAction();
    }

    public void update() {

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (onPath == false && tileDistance < 9) {

            int i = new Random().nextInt(100)+1;
            if (i > 50) {
                onPath = true;
            }
        }
        if (onPath == true && tileDistance > 50) {

            onPath = false;
        }

    }

    public void getImage() {

        up1 = setup("monster/goblin_boss_up_1", gp.tileSize*3, gp.tileSize*3);
        up2 = setup("monster/goblin_boss_up_2", gp.tileSize*3, gp.tileSize*3);
        down1 = setup("monster/goblin_boss_down_1", gp.tileSize*3, gp.tileSize*3);
        down2 = setup("monster/goblin_boss_down_2", gp.tileSize*3, gp.tileSize*3);
        left1 = setup("monster/goblin_boss_left_1", gp.tileSize*3, gp.tileSize*3);
        left2 = setup("monster/goblin_boss_left_2", gp.tileSize*3, gp.tileSize*3);
        right1 = setup("monster/goblin_boss_right_1", gp.tileSize*3, gp.tileSize*3);
        right2 = setup("monster/goblin_boss_right_2", gp.tileSize*3, gp.tileSize*3);
    }



    public void setAction(){

        if (onPath == true) {


            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));


        }
        else if (onPath == false){

            actionLockCounter++;

            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
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

    }

    public void damageReaction() {

        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop() {

        int i = new Random().nextInt(100)+1;
        int j = new Random().nextInt(10)+1;

        // Drop
        if (i < 50) {
            dropItem(new OBJ_Key(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Key(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Key(gp));
        }
    }
}
