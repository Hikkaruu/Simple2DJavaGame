package main;

import entity.Entity;
import entity.Particle;
import entity.Player;
import entity.Projectile;
import monster.MON_Goblin;
import monster.MON_GreenSlime;
import monster.MON_Pyromancer;
import object.OBJ_Fireball;
import object.OBJ_Sword_Projectile;
import tile_interactive.IT_Trunk;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true, usedHealing = false;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 21;
            eventRect[map][col][row].y = 21;
            eventRect[map][col][row].width = 50;
            eventRect[map][col][row].height = 50;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

    }

    public void checkEvent(){

        // Sprawdzanie czy gracz jest dalej niż jedna kratke od eventu
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize+32) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true){
            if(hit(0,34, 8, "any") == true) {healingPool(gp.dialogueState);}
            else if(hit(0, 19, 16, "any") == true) {teleport(1, 12, 13);}
            else if(hit(1, 12, 13, "any") == true) {teleport(0, 19,16);}
            else if(hit(1, 12, 9, "up") == true) {speak(gp.npc[1][0]);}
            // tp w krysztle na mapie 0
            else if(hit(0, 23, 9, "any") == true) {teleport(2, 6, 48);}
            else if(hit(0, 32, 15, "any") == true) {teleport(3, 10, 30);}
            else if(hit(3, 9, 30, "any") == true) {teleport(0, 33, 15);}
            else if(hit(2, 11, 43, "any") == true) {projectile(2,22, 43, "left", new OBJ_Sword_Projectile(gp));}
            else if(hit(2, 45, 39, "any") == true) {teleport(2, 46, 22);}
            else if(hit(2, 48, 20, "any") == true) {teleport(2, 5, 4);}
            else if(hit(2, 4, 1, "any") == true) {teleport(2, 46, 38);}
            else if(hit(2, 33, 11, "up") == true) {teleport(4, 26,32);}
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;

        if (map == gp.currentMap) {

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {

                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void teleport(int map, int col, int row) {

        eventRect[map][col][row].x = 0;
        eventRect[map][col][row].y = 0;
        eventRect[map][col][row].width = 30;
        eventRect[map][col][row].height = 30;
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(15);
    }

    public void speak(Entity entity) {

        if (gp.keyH.spacePressed == true) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 3;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void projectile(int map, int col, int row, String direction, Projectile projectile) {

        Entity mob = new MON_Pyromancer(gp, 1, 1);
        Projectile proj = projectile;
        projectile.set(col*gp.tileSize, row* gp.tileSize, direction, true, mob);
        gp.projectileList.add(proj);
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {

        if(gp.keyH.spacePressed == true && usedHealing == false) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            if (gp.player.life == gp.player.maxLife){
                gp.ui.currentDialogue = "Your health is full!";
                //gp.aSetter.setMonster();
            }
            else{
                gp.ui.currentDialogue = "You absorb the essence.\nYour life and mana have been restored.";
                gp.playSE(2);
                gp.player.life = gp.player.maxLife;
                gp.player.mana = gp.player.maxMana;
                usedHealing = true;
                gp.iTile[gp.currentMap][8] = null;
            }

        }
    }


}
