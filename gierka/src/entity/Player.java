package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean canUse;
    public int whatArrow;
    public int spawnX, spawnY;
    public Projectile arrowNormal, arrowNight;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(22, 34, 20, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //attackArea.width = 64;
        //attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 5;
        direction = "down";

        spawnX = worldX;
        spawnY = worldY;

        // Staty gracza
        level = 1;
        maxLife = 25;
        life = maxLife;
        maxMana = 5;
        mana = maxMana;
        strength = 4;
        dexterity = 4;
        inteligence = 3;
        endurance = 1;
        exp = 0;
        nextLevelExp = 10;
        statPoints = 2;
        coin = 6000;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentAmmo = new OBJ_Null(gp);
        fireball = new OBJ_Fireball(gp);
        ammo = currentAmmo.ammo;
        projectile = fireball;
        projectileArrow = new  OBJ_Arrow_Normal(gp);
        attack = getAttack(); // obliczenia dmg
        defense = getDefense(); // obliczenia defa

        // Rodzaje strzał
        arrowNormal = new OBJ_Arrow_Normal(gp);
        arrowNight = new OBJ_Arrow_Night(gp);
    }

    public void setDefaultPositions() {

        worldX = spawnX;
        worldY = spawnY;
        direction = "down";
    }

    public void restoreLifeAndMana() {

        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void setItems() {

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Bow_Blue(gp));
        inventory.add(new OBJ_Arrow_Normal(gp));

    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        if (currentWeapon.type != type_bow){
            attack = strength + currentWeapon.attackValue;
        }
        if (currentWeapon.type == type_bow){
            attack = dexterity + currentAmmo.attackValue + currentWeapon.attackValue;
        }
        return attack;
    }

    public int getDefense(){
        return defense = endurance + currentShield.defenseValue;
    }

    public void getPlayerImage(){

        up1 = setup("player/elf_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("player/elf_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("player/elf_up_3", gp.tileSize, gp.tileSize);
        down1 = setup("player/elf_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("player/elf_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("player/elf_down_3", gp.tileSize, gp.tileSize);
        left1 = setup("player/elf_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("player/elf_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("player/elf_left_3", gp.tileSize, gp.tileSize);
        right1 = setup("player/elf_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("player/elf_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("player/elf_right_3", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage(){

        if(currentWeapon.type == type_sword) {
            attackUp1 = setup("player/elf_sword_up_1", gp.tileSize*2, gp.tileSize*2);
            attackUp2 = setup("player/elf_sword_up_2", gp.tileSize*2, gp.tileSize*2);
            attackUp3 = setup("player/elf_sword_up_3", gp.tileSize*2, gp.tileSize*2);
            attackUp4 = setup("player/elf_sword_up_4", gp.tileSize*2, gp.tileSize*2);

            attackDown1 = setup("player/elf_sword_down_1", gp.tileSize*2, gp.tileSize*2);
            attackDown2 = setup("player/elf_sword_down_2", gp.tileSize*2, gp.tileSize*2);
            attackDown3 = setup("player/elf_sword_down_3", gp.tileSize*2, gp.tileSize*2);
            attackDown4 = setup("player/elf_sword_down_4", gp.tileSize*2, gp.tileSize*2);

            attackLeft1 = setup("player/elf_sword_left_1", gp.tileSize*2, gp.tileSize*2);
            attackLeft2 = setup("player/elf_sword_left_2", gp.tileSize*2, gp.tileSize*2);
            attackLeft3 = setup("player/elf_sword_left_3", gp.tileSize*2, gp.tileSize*2);
            attackLeft4 = setup("player/elf_sword_left_4", gp.tileSize*2, gp.tileSize*2);

            attackRight1 = setup("player/elf_sword_right_1", gp.tileSize*2, gp.tileSize*2);
            attackRight2 = setup("player/elf_sword_right_2", gp.tileSize*2, gp.tileSize*2);
            attackRight3 = setup("player/elf_sword_right_3", gp.tileSize*2, gp.tileSize*2);
            attackRight4 = setup("player/elf_sword_right_4", gp.tileSize*2, gp.tileSize*2);
        }
        if(currentWeapon.type == type_axe) {
            attackUp1 = setup("player/elf_axe_up_1", gp.tileSize*2, gp.tileSize*2);
            attackUp2 = setup("player/elf_axe_up_2", gp.tileSize*2, gp.tileSize*2);
            attackUp3 = setup("player/elf_axe_up_3", gp.tileSize*2, gp.tileSize*2);
            attackUp4 = setup("player/elf_axe_up_4", gp.tileSize*2, gp.tileSize*2);

            attackDown1 = setup("player/elf_axe_down_1", gp.tileSize*2, gp.tileSize*2);
            attackDown2 = setup("player/elf_axe_down_2", gp.tileSize*2, gp.tileSize*2);
            attackDown3 = setup("player/elf_axe_down_3", gp.tileSize*2, gp.tileSize*2);
            attackDown4 = setup("player/elf_axe_down_4", gp.tileSize*2, gp.tileSize*2);

            attackLeft1 = setup("player/elf_axe_left_1", gp.tileSize*2, gp.tileSize*2);
            attackLeft2 = setup("player/elf_axe_left_2", gp.tileSize*2, gp.tileSize*2);
            attackLeft3 = setup("player/elf_axe_left_3", gp.tileSize*2, gp.tileSize*2);
            attackLeft4 = setup("player/elf_axe_left_4", gp.tileSize*2, gp.tileSize*2);

            attackRight1 = setup("player/elf_axe_right_1", gp.tileSize*2, gp.tileSize*2);
            attackRight2 = setup("player/elf_axe_right_2", gp.tileSize*2, gp.tileSize*2);
            attackRight3 = setup("player/elf_axe_right_3", gp.tileSize*2, gp.tileSize*2);
            attackRight4 = setup("player/elf_axe_right_4", gp.tileSize*2, gp.tileSize*2);
        }
        if(currentWeapon.type == type_bow) {
            attackUp1 = setup("player/elf_bow_up_1", gp.tileSize*2, gp.tileSize*2);
            attackUp2 = setup("player/elf_bow_up_2", gp.tileSize*2, gp.tileSize*2);
            attackUp3 = setup("player/elf_bow_up_3", gp.tileSize*2, gp.tileSize*2);
            attackUp4 = setup("player/elf_bow_up_4", gp.tileSize*2, gp.tileSize*2);

            attackDown1 = setup("player/elf_bow_down_1", gp.tileSize*2, gp.tileSize*2);
            attackDown2 = setup("player/elf_bow_down_2", gp.tileSize*2, gp.tileSize*2);
            attackDown3 = setup("player/elf_bow_down_3", gp.tileSize*2, gp.tileSize*2);
            attackDown4 = setup("player/elf_bow_down_4", gp.tileSize*2, gp.tileSize*2);

            attackLeft1 = setup("player/elf_bow_left_1", gp.tileSize*2, gp.tileSize*2);
            attackLeft2 = setup("player/elf_bow_left_2", gp.tileSize*2, gp.tileSize*2);
            attackLeft3 = setup("player/elf_bow_left_3", gp.tileSize*2, gp.tileSize*2);
            attackLeft4 = setup("player/elf_bow_left_4", gp.tileSize*2, gp.tileSize*2);

            attackRight1 = setup("player/elf_bow_right_1", gp.tileSize*2, gp.tileSize*2);
            attackRight2 = setup("player/elf_bow_right_2", gp.tileSize*2, gp.tileSize*2);
            attackRight3 = setup("player/elf_bow_right_3", gp.tileSize*2, gp.tileSize*2);
            attackRight4 = setup("player/elf_bow_right_4", gp.tileSize*2, gp.tileSize*2);
        }




    }

    public void update() {

        if (attacking == true) {
            attacking();
        }
        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||
                keyH.rightPressed == true || gp.keyH.spacePressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // Sprawdzanie kolizji z texturami
            collisionOn = false;
            gp.cChecker.checkTile(this);
            // Sprawdzanie kolizji z objektem
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Sprawdzanie kolizji z NPC
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Sprawdzanie kolizji z mobem
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Sprawdzanie kolizji z interaktywnymi texturami
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            // Sprawdzanie wywołania Eventu
            gp.eHandler.checkEvent();


            // Jesli kolizja ma wartosc falsz - gracz moze sie ruszac
            if (collisionOn == false && gp.keyH.spacePressed == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if (keyH.spacePressed == true && attackCanceled == false) {
                if (gp.player.currentWeapon.type != type_bow){
                    gp.playSE(7);
                }
                else {
                    gp.playSE(12);
                }
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            if (gp.keyH.spacePressed == true){
            }
            else {
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
            }
            gp.keyH.spacePressed = false;
        }
        else {
            standCounter++;
            if (standCounter == 10){
                spriteNum = 3;
                standCounter = 0;
            }
        }

        // Spell
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 &&
                projectile.haveResource(this) == true) {

            // Podstawowe kordy, kierunek i użytkownik
            projectile.set(worldX, worldY, direction, true, this);

            // Ściąganie many
            projectile.subtractResource(this);

            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(10);
        }


        switch (whatArrow){
            //ammoTypeNormalArrow;
            case 0:
                projectileArrow = arrowNormal;
                break;
            //ammoTypeNightArrow;
            case 1:
                projectileArrow = arrowNight;
                break;
        }


        // Łuk
        if(gp.keyH.weaponShotKeyPressed == true && projectileArrow.alive == false && weaponShotAvailableCounter == 50) {

            if (spriteCounter > 45 && projectileArrow.haveResource(this) == true) {
                    projectileArrow.set(worldX, worldY, direction, true, this);

                    // Ściąganie strzał
                    projectileArrow.subtractResource(this);

                    gp.projectileList.add(projectileArrow);

                    weaponShotAvailableCounter = 0;

                    gp.keyH.spacePressed = false;
                    gp.keyH.weaponShotKeyPressed = false;
                }
        }


        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (weaponShotAvailableCounter < 50) {
            weaponShotAvailableCounter++;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playMusic(14);
        }
    }

    public void damageInteractiveTile(int i) {

        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
        && gp.iTile[gp.currentMap][i].invincible == false) {

            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            // Generowanie cząsteczek
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
                int r = new Random().nextInt(100)+1;
                int j = new Random().nextInt(10)+1;
                if (r < 50) {
                    this.dropItem(new OBJ_Coin_Gold(gp, j));
                }
                if (r >= 50 && r < 75) {
                    this.dropItem(new OBJ_Potion_Red(gp));
                }
                if (r >= 75 && r < 100) {
                    this.dropItem(new OBJ_Potion_Blue(gp));
                }
            }
        }
    }
    public void pickUpObject(int i){

        if (i != 999 && gp.obj[gp.currentMap][i].mechObj == false) {

            if (gp.obj[gp.currentMap][i].name == "Skeleton Head" && i != 999){
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
            }

            // Podnoszenie
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // Podnoszenie do eq
            else {

                String text;

                inventory.add(gp.obj[gp.currentMap][i]);
                gp.playSE(1);
                text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
        else if (i != 999 && gp.obj[gp.currentMap][i].mechObj == true){

            int z=0;
            boolean noKey = false;
            for (int j=0; j<inventory.size(); j++){
                if (inventory.get(j).name == "Key"){
                    z = j;
                    noKey = false;
                    break;
                }
                else {
                    noKey = true;
                }
            }
            if (noKey == false) {
                gp.obj[gp.currentMap][i] = null;
                inventory.remove(z);
            }
            else if (noKey == true) {
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "You got no key!";
            }



        }
    }

    public void interactNPC(int i){

        if (gp.keyH.spacePressed == true) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
                gp.keyH.spacePressed = false;
            }
        }
    }

    public void contactMonster(int i){

        if (i != 999){

            if (invincible == false && gp.monster[gp.currentMap][i].dying == false){
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage <= 0) {
                    damage = 1;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {

        if (i != 999) {

            if (gp.monster[gp.currentMap][i].invincible == false){

                // dźwięk tylko wtedy gdy uderzam bronią białą
                if (projectile.alive == false && projectileArrow.alive == false){
                    gp.playSE(5);
                }
                if (projectile.alive == false){
                    gp.playSE(11);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " dmg!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage(gp.monster[gp.currentMap][i].exp + " Exp");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp(){

        if (exp >= nextLevelExp) {

            level++;
            statPoints += 2;
            exp = exp - nextLevelExp;
            nextLevelExp = nextLevelExp*2;
            maxLife += 5;
            maxMana += 1;
            life = maxLife;
            mana = maxMana;

            gp.playSE(8);
            gp.gameState = gp.lvlUpState;
        }
    }

    public void selectItem() {

        int itemIndex = 0;

        itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow) + 20*gp.ui.playerSlotPage;

        if(itemIndex < inventory.size()){

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_bow) {

                if (selectedItem.type == type_bow){

                    currentWeapon = selectedItem;
                    currentShield = new OBJ_Null(gp);
                    attack = getAttack();
                    defense = getDefense();
                    getPlayerAttackImage();
                }
                else {
                    currentWeapon = selectedItem;
                    attack = getAttack();
                    getPlayerAttackImage();
                }

            }
            if (selectedItem.type == type_shield && currentWeapon.type != type_bow) {

                    currentShield = selectedItem;
                    attack = getAttack();
                    defense = getDefense();

            }
            if (selectedItem.type == type_consumable) {

                selectedItem.use(this);
                if (canUse ==  true){
                    inventory.remove(itemIndex);
                }
            }
            if (selectedItem.type == type_arrow && currentWeapon.type == type_bow) {

                if (selectedItem.ammoType == currentAmmo.ammoType) {
                    currentAmmo = selectedItem;
                    currentWeapon.ammoType = selectedItem.ammoType;
                    currentShield = new OBJ_Null(gp);
                    ammo += selectedItem.ammo;
                    inventory.remove(itemIndex);
                    attack = getAttack();
                    defense = getDefense();
                    whatArrow = currentAmmo.ammoType;
                }
                if (selectedItem.ammoType != currentAmmo.ammoType) {

                    currentAmmo = selectedItem;
                    currentWeapon.ammoType = selectedItem.ammoType;
                    currentShield = new OBJ_Null(gp);
                    ammo = selectedItem.ammo;
                    inventory.remove(itemIndex);
                    attack = getAttack();
                    defense = getDefense();
                    whatArrow = currentAmmo.ammoType;
                }
            }

        }
    }

    public  void draw(Graphics2D g2){

        BufferedImage image = null;
        // Zapobiegniecie rysowania broni w miejscu postaci
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if (attacking == false) {
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                    if(spriteNum == 3) {image = up3;}
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

        if (invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alfy

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG

        //Sprawdzanie invincible
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible:"+invincibleCounter, 10, 400);

        //Wyswietlanie prostokata sluzacego do wykrywania kolizji
        //g2.setColor(Color.red);
        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


        // Hitbox miecza (attackArea)
        /*
        tempScreenX = screenX + solidArea.x;
        tempScreenY = screenY + solidArea.y;
        switch(direction) {
            case "up":
                tempScreenX = screenX + 1;
                tempScreenY = screenY - attackArea.height;
                break;
            case "down":
                tempScreenX = screenX + 1;
                tempScreenY = screenY + attackArea.height;
                break;
            case "left":
                tempScreenX = screenX - attackArea.width;
                tempScreenY = screenY + 2;
                break;
            case "right":
                tempScreenX = screenX + attackArea.width;
                tempScreenY = screenY + 2;
                break;
        }
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
        */

        /*
        // Sprawdzanie ekwipunku
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("slotCol:"+gp.ui.slotCol, 10, 400);
        g2.drawString("slotRow:"+gp.ui.slotRow, 10, 500);
         */

        /*
        // Strzelanie z łuku
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("strzelanie: "+gp.keyH.weaponShotKeyPressed, 10, 400);
        g2.drawString("sprite counter: "+spriteCounter, 10, 500);
        */

    }
}
