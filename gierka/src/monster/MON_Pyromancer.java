package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Pyromancer extends Entity {

    GamePanel gp;
    int spawnX, spawnY;
    public MON_Pyromancer(GamePanel gp, int spawnX, int spawnY) {
        super(gp);

        this.gp = gp;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        type = type_monster;
        name = "Pyromancer";
        speed = 2;
        maxLife = 23;
        life = maxLife;
        attack = 9;
        defense = 3;
        exp = 27;
        projectile = new OBJ_Fireball2(gp);
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

        if (onPath == false && tileDistance < 7) {

            int i = new Random().nextInt(100)+1;
            if (i > 50) {
                onPath = true;
            }
        }
        if (onPath == true && tileDistance > 14) {

            onPath = false;
            atSpawn = true;
        }

    }

    public void getImage() {

        up1 = setup("monster/pyromancer_up_2", gp.tileSize, gp.tileSize);
        up2 = setup("monster/pyromancer_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/pyromancer_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/pyromancer_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/pyromancer_left_2", gp.tileSize, gp.tileSize);
        left2 = setup("monster/pyromancer_left_3", gp.tileSize, gp.tileSize);
        right1 = setup("monster/pyromancer_right_2", gp.tileSize, gp.tileSize);
        right2 = setup("monster/pyromancer_right_3", gp.tileSize, gp.tileSize);
    }

    public void setAction(){

        if (onPath == true && atSpawn == false) {

            //int goalCol = 10;
            //int goalRow = 10;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);

            int x = new Random().nextInt(200)+1;
            if (x > 193 && projectile.alive == false && shotAvailableCounter == 30) {

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
        int j = new Random().nextInt(30)+1;
        // Drop
        if (i < 50) {
            dropItem(new OBJ_Coin_Gold(gp, j));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Potion_Red(gp));
            dropItem(new OBJ_Potion_Blue(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Shield_Blue(gp));
        }
    }
}
