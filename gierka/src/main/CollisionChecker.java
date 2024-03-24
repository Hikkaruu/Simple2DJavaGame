package main;

import entity.Entity;
import entity.Projectile;
import tile.TileManager;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        double entityLeftWorldX = entity.worldX + entity.solidArea.x;
        double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        double entityTopWorldY = entity.worldY + entity.solidArea.y;
        double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        double entityLeftCol = entityLeftWorldX/gp.tileSize;
        double entityRightCol = entityRightWorldX/gp.tileSize;
        double entityTopRow = entityTopWorldY/gp.tileSize;
        double entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkTileProjectile(Projectile entity){


        double entityLeftWorldX = entity.worldX + entity.projectileSolidArea.x;
        double entityRightWorldX = entity.worldX + entity.projectileSolidArea.x + entity.projectileSolidArea.width;
        double entityTopWorldY = entity.worldY + entity.projectileSolidArea.y;
        double entityBottomWorldY = entity.worldY + entity.projectileSolidArea.y + entity.projectileSolidArea.height;

        double entityLeftCol = entityLeftWorldX/gp.tileSize;
        double entityRightCol = entityRightWorldX/gp.tileSize;
        double entityTopRow = entityTopWorldY/gp.tileSize;
        double entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityTopRow];
                if (gp.tileM.tile[tileNum1].collisionProjectile == true || gp.tileM.tile[tileNum2].collisionProjectile == true) {

                        entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityBottomRow];
                if (gp.tileM.tile[tileNum1].collisionProjectile == true || gp.tileM.tile[tileNum2].collisionProjectile == true) {

                        entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityLeftCol][(int)entityBottomRow];
                if (gp.tileM.tile[tileNum1].collisionProjectile == true || gp.tileM.tile[tileNum2].collisionProjectile == true) {

                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][(int)entityRightCol][(int)entityBottomRow];
                if (gp.tileM.tile[tileNum1].collisionProjectile == true || gp.tileM.tile[tileNum2].collisionProjectile == true) {

                        entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj[1].length; i++){

            if (gp.obj[gp.currentMap][i] != null){

                // Pozycja entity
                entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);

                // Pozycja objektu
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch (entity.direction){
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                    if (gp.obj[gp.currentMap][i].collision == true){
                        entity.collisionOn = true;
                    }
                    if (player == true){
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // NPC i Moby
    public int checkEntity(Entity entity, Entity[][] target){


        int index = 999;

        for (int i = 0; i < target[1].length; i++){

            if (target[gp.currentMap][i] != null && target[gp.currentMap][i].invincible != true && target[gp.currentMap][i].dying != true){

                // Pozycja entity
                entity.solidArea.x = (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (entity.worldY + entity.solidArea.y);

                // Pozycja targetu
                target[gp.currentMap][i].solidArea.x = (target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x);
                target[gp.currentMap][i].solidArea.y = (target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y);

                switch (entity.direction){
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){

                    if (target[gp.currentMap][i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        // Pozycja entity
        entity.solidArea.x = (entity.worldX + entity.solidArea.x);
        entity.solidArea.y = (entity.worldY + entity.solidArea.y);

        // Pozycja objektu
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction){
            case "up": entity.solidArea.y -= entity.speed; break;
            case "down": entity.solidArea.y += entity.speed; break;
            case "left": entity.solidArea.x -= entity.speed; break;
            case "right": entity.solidArea.x += entity.speed; break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)){

            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }

}
