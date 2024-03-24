package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3,  up4, up5, down4, down5, left4, left5, right4, right5;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackDown1, attackDown2, attackDown3, attackDown4,
            attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackRight1, attackRight2, attackRight3, attackRight4;
    public BufferedImage image;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[50];


    // countery
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;

    public int weaponShotAvailableCounter = 0;

    // State'y
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean atSpawn = false;
    public boolean mechObj = false;


    // Staty postaci

    public String name;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int statPoints;
    public int speed;
    public int level;
    public int strength;
    public int dexterity;
    public int inteligence;
    public int endurance;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentAmmo;
    public Entity currentShield;
    public Projectile projectile, projectileArrow;
    public Projectile fireball, arrow_normal;

    // Staty itemów
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;

    // typ
    public int type;
    public boolean isBoss;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_bow = 7;
    public final int type_arrow = 8;
    public final int type_null = 9;
    public final int type_spell = 10;
    public final int type_pickupOnly = 11;

    public int ammoType;
    public final int ammoTypeNormalArrow = 0;
    public final int ammoTypeNightArrow = 1;



    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int getXdistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }

    public int getYdistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }

    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target));
        return tileDistance;
    }

    public int getGoalCol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }

    public int getGoalRow(Entity target) {
        int goalCol = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalCol;
    }

    public void setAction() {

    }

    public void damageReaction() {

    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void use(Entity entity) {

    }

    public void checkDrop() {

    }

    public void dropItem(Entity droppedItem) {

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; // położenie zabitego moba
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {

        Color color = null;
        return color;
    }

    public int getParticleSize() {

        int size = 0; // px
        return size;
    }

    public int getParticleSpeed() {

        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {

        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size,speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size,speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size,speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size,speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void checkCollision() {

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true){

            damagePlayer(attack);
        }
    }

    public void update(){

        if (attacking == true) {

            attacking();
        }

        setAction();
        checkCollision();

        // Jesli kolizja ma wartosc falsz - gracz moze sie ruszac

        if (collisionOn == false){
                switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void damagePlayer(int attack) {

        if (gp.player.invincible == false) {
            // zadawanie dmg
            gp.playSE(6);

            int damage = attack - gp.player.defense;
            if (damage < 0) {
                damage = 0;
            }
            gp.player.life -= damage;

            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            if (type == type_monster)
            {
                switch (direction){
                    case "up":
                        if (attacking == false) {
                            if(spriteNum == 1) {image = up1;}
                            if(spriteNum == 2) {image = up2;}
                        }
                        if (attacking == true) {
                            tempScreenY = screenY - gp.tileSize;
                            if(spriteNum == 1) {image = attackUp1;}
                            if(spriteNum == 2) {image = attackUp2;}
                        }
                        break;
                    case "down":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = down1;}
                            if (spriteNum == 2) {image = down2;}
                        }
                        if (attacking == true)  {
                            if(spriteNum == 1) {image = attackDown1;}
                            if(spriteNum == 2) {image = attackDown2;}
                            if(spriteNum == 3) {image = attackDown3;}
                            if(spriteNum == 4) {image = attackDown4;}
                        }
                        break;
                    case "left":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = left1;}
                            if (spriteNum == 2) {image = left2;}
                        }
                        if (attacking == true) {
                            tempScreenX = screenX - gp.tileSize;
                            tempScreenY = screenY - gp.tileSize;
                            if(spriteNum == 1) {image = attackLeft1;}
                            if(spriteNum == 2) {image = attackLeft2;}
                        }
                        break;
                    case "right":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = right1;}
                            if (spriteNum == 2) {image = right2;}
                        }
                        if (attacking == true){
                            tempScreenY = screenY - gp.tileSize;
                            if(spriteNum == 1) {image = attackRight1;}
                            if(spriteNum == 2) {image = attackRight2;}
                        }
                        break;
                }
            }
            else {
                switch (direction){
                    case "up":
                        if (attacking == false) {
                            if(spriteNum == 1) {image = up1;}
                            if(spriteNum == 2) {image = up2;}
                            if(spriteNum == 3) {image = up3;}
                            if(spriteNum == 4) {image = up4;}
                            if(spriteNum == 5) {image = up5;}
                        }
                        if (attacking == true) {
                            tempScreenY = screenY - gp.tileSize;
                            if(spriteNum == 1) {image = attackUp1;}
                            if(spriteNum == 2) {image = attackUp2;}
                            if(spriteNum == 3) {image = attackUp3;}
                            if(spriteNum == 4) {image = attackUp4;}
                        }
                        break;
                    case "down":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = down1;}
                            if (spriteNum == 2) {image = down2;}
                            if (spriteNum == 3) {image = down3;}
                        }
                        if (attacking == true)  {
                            if(spriteNum == 1) {image = attackDown1;}
                            if(spriteNum == 2) {image = attackDown2;}
                            if(spriteNum == 3) {image = attackDown3;}
                            if(spriteNum == 4) {image = attackDown4;}
                        }
                        break;
                    case "left":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = left1;}
                            if (spriteNum == 2) {image = left2;}
                            if (spriteNum == 3) {image = left3;}
                        }
                        if (attacking == true) {
                            tempScreenX = screenX - gp.tileSize;
                            tempScreenY = screenY - gp.tileSize;
                            if(spriteNum == 1) {image = attackLeft1;}
                            if(spriteNum == 2) {image = attackLeft2;}
                            if(spriteNum == 3) {image = attackLeft3;}
                            if(spriteNum == 4) {image = attackLeft4;}
                        }
                        break;
                    case "right":
                        if (attacking == false) {
                            if (spriteNum == 1) {image = right1;}
                            if (spriteNum == 2) {image = right2;}
                            if (spriteNum == 3) {image = right3;}
                        }
                        if (attacking == true){
                            tempScreenY = screenY - gp.tileSize;
                            if(spriteNum == 1) {image = attackRight1;}
                            if(spriteNum == 2) {image = attackRight2;}
                            if(spriteNum == 3) {image = attackRight3;}
                            if(spriteNum == 4) {image = attackRight4;}

                        }
                        break;
                }
            }


            // HP bar mobów
            if (type == 2 && hpBarOn == true && isBoss == false) {

                if(life < 0){
                    life = 0;
                }
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                String hpBar = ""+life+"";

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                g2.setColor(Color.WHITE);
                g2.drawString(hpBar, screenX+28, screenY-5);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            else if (isBoss == true && type == 2 && hpBarOn == true) {

                if(life < 0){
                    life = 0;
                }
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                String hpBar = ""+life+"";

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX+63, screenY-16, gp.tileSize+2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX+64, screenY - 15, (int)hpBarValue, 10);
                g2.setColor(Color.WHITE);
                g2.drawString(hpBar, screenX+90, screenY-5);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY,null);

            changeAlpha(g2, 1f);
        }

    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage setup2(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height+32);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search() == true) {

            // Następne worldX i worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left or right
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // down or right
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }

            // gdy osiagnie goal stopuje szukanie
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }

        }
    }

    public void searchPath2(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x*2) / gp.tileSize;
        int startRow = (worldY + solidArea.y*2) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search() == true) {

            // Następne worldX i worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //
            int enLeftX = worldX + solidArea.x*2;
            int enRightX = worldX + solidArea.x*2 + solidArea.width*2;
            int enTopY = worldY + solidArea.y*2;
            int enBottomY = worldY + solidArea.y*2 + solidArea.height*2;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left or right
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // down or right
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }

            // gdy osiagnie goal stopuje szukanie
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }

        }
    }

    public void attacking(){

        if (gp.player.currentWeapon.type != type_bow && type != type_monster){

            spriteCounter++;

            if (spriteCounter <= 6) {
                spriteNum = 1;
            }
            if (spriteCounter > 6 && spriteCounter <= 12) {
                spriteNum = 2;
                attackCheck();
            }
            if (spriteCounter > 12 && spriteCounter <= 18) {
                spriteNum = 3;
                attackCheck();
            }
            if (spriteCounter > 18 && spriteCounter <= 24) {
                spriteNum = 4;
                attackCheck();
            }
            if (spriteCounter > 24){
                spriteNum = 1;
                spriteCounter = 0;
                attacking = false;
            }
        }
        else if (gp.player.currentWeapon.type == type_bow && type != type_monster) {

            spriteCounter++;

            if (spriteCounter <= 15) {
                spriteNum = 1;
            }
            if (spriteCounter > 15 && spriteCounter <= 30) {
                spriteNum = 2;
                attackCheck();
            }
            if (spriteCounter > 30 && spriteCounter <= 45) {
                spriteNum = 3;
                attackCheck();
            }
            if (spriteCounter > 45 && spriteCounter <= 60 && gp.keyH.weaponShotKeyPressed == true) {
                spriteNum = 4;
                attackCheck();
            }
            if (spriteCounter > 60 && gp.keyH.shotKeyPressed == false){
                spriteNum = 1;
                spriteCounter = 0;
                attacking = false;
            }
        }
        else if (type == type_monster) {

            spriteCounter++;

            if (spriteCounter <= 6) {
                spriteNum = 1;
            }
            if (spriteCounter > 6 && spriteCounter <= 12) {
                spriteNum = 2;
                attackCheck();
            }
            if (spriteCounter > 12 && spriteCounter <= 18) {
                spriteNum = 3;
                attackCheck();
            }
            if (spriteCounter > 18 && spriteCounter <= 24) {
                spriteNum = 4;
                attackCheck();
            }
            if (spriteCounter > 24) {
                spriteCounter = 0;
                attacking = false;
            }
        }
    }

    public void checkIfAttack(int rate, int straight, int horizontal) {

        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.worldX < worldX && yDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.worldX > worldX && yDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }

        if (targetInRange == true && attacking == false) {

            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }

        }

    }

    public void attackCheck(){
        // Zapisanie aktualnych pozycji, worldX, worldY, solidArea
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        // Dostosowanie worldX/Y do attackArea
        switch (direction){

            case "up":
                worldX += solidArea.width - gp.tileSize;
                worldY -= solidArea.height*3;
                break;
            case "down":
                worldX += solidArea.width - gp.tileSize;
                worldY += solidArea.height/2;
                break;
            case "left":
                worldX -= solidArea.width*4.5;
                worldY += solidArea.height - gp.tileSize;
                break;
            case "right":
                worldX += solidArea.width + 8;
                worldY += solidArea.height - gp.tileSize;
                break;
        }

        // attackArea staje się solidArea
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        if (type == type_monster) {

            if (gp.cChecker.checkPlayer(this) == true) {

                damagePlayer(attack);
            }
        }
        else {
            // Sprawdzenie kolizji z mobem na zaktualizowanych worldX/Y i solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            gp.player.damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            gp.player.damageInteractiveTile(iTileIndex);
        }


        // Przywrócenie oryginalnych wartosci po sprawdzeniu kolizji
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }


}
