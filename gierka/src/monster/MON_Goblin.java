package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Goblin extends Entity {

    GamePanel gp;
    int spawnX, spawnY;
    public MON_Goblin(GamePanel gp, int spawnX, int spawnY) {
        super(gp);

        this.gp = gp;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        type = type_monster;
        name = "Goblin";
        speed = 1;
        maxLife = 14;
        life = maxLife;
        attack = 6;
        defense = 0;
        exp = 6;
        projectile = new OBJ_Rock(gp);
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 40;
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

        // zasięg wykrywania gracza (5)
        if (onPath == false && tileDistance < 5) {

            int i = new Random().nextInt(100)+1;
            if (i > 50) {
                onPath = true;
            }
        }
        // jesli uciekniemy na 13+ kratek moby gubią agro
        if (onPath == true && tileDistance > 12) {

            onPath = false;
            atSpawn = true;
        }

    }

    public void getImage() {

        up1 = setup("monster/goblin_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/goblin_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/goblin_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/goblin_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/goblin_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/goblin_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/goblin_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/goblin_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){

        if (onPath == true && atSpawn == false) {

            //int goalCol = 10;
            //int goalRow = 10;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);

            int x = new Random().nextInt(200)+1;
            if (x > 197 && projectile.alive == false && shotAvailableCounter == 30) {

                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
        }
        else if (onPath == false && atSpawn == false){

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
        else if (atSpawn == true && onPath == false) {

            // moby po zgubieniu agro wracaja na respa
            searchPath(24, 37);
            actionLockCounter++;
            if (actionLockCounter == 540) {
                actionLockCounter = 0;
                atSpawn = false;
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
            dropItem(new OBJ_Coin_Gold(gp, j));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Potion_Red(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Axe(gp));
        }
    }
}
