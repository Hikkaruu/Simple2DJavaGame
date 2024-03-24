package main;

import entity.Entity;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.*;
import object.*;
import tile_interactive.IT_Barrel;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_HealOrb;
import tile_interactive.IT_Skeleton;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*18;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*23;
        gp.obj[mapNum][i].worldY = gp.tileSize*11;
        i++;

        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*2;
        gp.obj[mapNum][i].worldY = gp.tileSize*44;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*11;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*46;
        gp.obj[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*47;
        gp.obj[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*46;
        gp.obj[mapNum][i].worldY = gp.tileSize*47;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*47;
        gp.obj[mapNum][i].worldY = gp.tileSize*47;
        i++;
        gp.obj[mapNum][i] = new OBJ_Arrow_Night(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*47;
        gp.obj[mapNum][i].worldY = gp.tileSize*39;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*46;
        gp.obj[mapNum][i].worldY = gp.tileSize*37;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*47;
        gp.obj[mapNum][i].worldY = gp.tileSize*46;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*5;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Arrow_Night(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*46;
        gp.obj[mapNum][i].worldY = gp.tileSize*46;
        i++;
        gp.obj[mapNum][i] = new OBJ_Sword_Loopuster(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*40;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;



    }

    public void setNPC(){

        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*11;
        gp.npc[mapNum][i].worldY = gp.tileSize*31;
        i++;

        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*4;
        gp.npc[mapNum][i].worldY = gp.tileSize*3;
        i++;
    }

    public void setMonster(){

        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 15, 38);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*38;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 16, 39);
        gp.monster[mapNum][i].worldX = gp.tileSize*16;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 35, 41);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*41;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 36, 38);
        gp.monster[mapNum][i].worldX = gp.tileSize*36;
        gp.monster[mapNum][i].worldY = gp.tileSize*38;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 32, 39);
        gp.monster[mapNum][i].worldX = gp.tileSize*32;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 40, 8);
        gp.monster[mapNum][i].worldX = gp.tileSize*40;
        gp.monster[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp, 38, 14);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*14;
        i++;
        gp.monster[mapNum][i] = new MON_Goblin(gp, 19, 31);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*31;
        i++;
        gp.monster[mapNum][i] = new MON_Goblin(gp, 20, 31);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*31;
        i++;


        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*12;
        gp.npc[mapNum][i].worldY = gp.tileSize*7;
        i++;

        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MON_GoblinKing(gp, 2, 36);
        gp.monster[mapNum][i].worldX = gp.tileSize*2;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new MON_GoblinKing(gp, 20, 45);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.monster[mapNum][i] = new MON_GoblinKing(gp, 24, 44);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*44;
        i++;
        gp.monster[mapNum][i] = new MON_GoblinKing(gp, 31, 44);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*44;
        i++;
        gp.monster[mapNum][i] = new MON_GoblinKing(gp, 41, 45);
        gp.monster[mapNum][i].worldX = gp.tileSize*41;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.monster[mapNum][i] = new MON_Pyromancer(gp, 43, 32);
        gp.monster[mapNum][i].worldX = gp.tileSize*43;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new MON_Pyromancer(gp, 32, 32);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new MON_Pyromancer(gp, 32, 32);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new MON_Pyromancer(gp, 19, 27);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
        i++;
        gp.monster[mapNum][i] = new MON_Skeleton(gp, 10, 19);
        gp.monster[mapNum][i].worldX = gp.tileSize*10;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        gp.monster[mapNum][i] = new MON_Pyromancer(gp, 21, 1);
        gp.monster[mapNum][i].worldX = gp.tileSize*21;
        gp.monster[mapNum][i].worldY = gp.tileSize*1;
        i++;
        gp.monster[mapNum][i] = new MON_Pyromancer(gp, 29, 7);
        gp.monster[mapNum][i].worldX = gp.tileSize*29;
        gp.monster[mapNum][i].worldY = gp.tileSize*7;
        i++;


        mapNum = 3;
        i = 0;
        gp.monster[mapNum][i] = new MON_GoblinKing(gp, 23, 25);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.monster[mapNum][i] = new MON_Goblin(gp, 34, 35);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;
        gp.monster[mapNum][i] = new MON_Goblin(gp, 19, 37);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;

        gp.monster[mapNum][i] = new MON_Goblin(gp, 18, 22);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*22;
        i++;

        gp.monster[mapNum][i] = new MON_Goblin(gp, 20, 18);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*18;
        i++;

        gp.monster[mapNum][i] = new MON_Goblin(gp, 26, 10);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;

        gp.monster[mapNum][i] = new MON_Goblin(gp, 13, 21);
        gp.monster[mapNum][i].worldX = gp.tileSize*13;
        gp.monster[mapNum][i].worldY = gp.tileSize*21;
        i++;

        mapNum = 4;
        i = 0;
        gp.monster[mapNum][i] = new MON_SkeletonAncient(gp, 26, 27);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
        i++;

    }

    public void setInteractiveTile() {

        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 21, 16); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 30); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 36, 32); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 32); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 33); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 34); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 35); i++;
        gp.iTile[mapNum][i] = new IT_HealOrb(gp, 34, 8); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 8, 19); i++;
        //gp.iTile[mapNum][i] = new IT_GrilleDoor(gp, 23, 11); i++;

        mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new IT_Skeleton(gp, 2, 44); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 30, 43); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 23, 46); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 24, 46); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 30, 34); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 39, 25); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 14, 28); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 15, 28); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 43, 7); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 43, 8); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 43, 9); i++;
        gp.iTile[mapNum][i] = new IT_Barrel(gp, 17, 8); i++;



    }

}
