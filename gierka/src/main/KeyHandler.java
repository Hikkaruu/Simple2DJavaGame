package main;

import entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, shotKeyPressed, weaponShotKeyPressed,
    statAddPressed;
    public boolean showGameInfo = true;
    public boolean showDebugText = false;
    public final int type_bow = 7;


    public KeyHandler(GamePanel gp){

        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Menu glowne
        if(gp.gameState == gp.titleState){

            titleState(code);
        }

        // Podczas gry
        else if(gp.gameState == gp.playState){

            playState(code);
        }

        // Pauza
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }

        // Dialogi
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }

        // Char menu
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }

        // Lvl up
        else if (gp.gameState == gp.lvlUpState) {
            selectionState(code);
        }
        // Opcje
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        // Game over
        else if(gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        // Trade
        else if(gp.gameState == gp.tradeState) {
            tradeState(code);
        }
    }

    public void titleState(int code) {

            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                    if (gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1){
                        // dodam później
                    }
                    if (gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }

            else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 6;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 6){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                    if (gp.ui.commandNum == 0){
                        statAddPressed = true;
                    }
                    if (gp.ui.commandNum == 1){
                        statAddPressed = true;
                    }
                    if (gp.ui.commandNum == 2){
                        statAddPressed = true;
                    }
                    if (gp.ui.commandNum == 3){
                        statAddPressed = true;
                    }
                    if (gp.ui.commandNum == 4){
                        //Accept
                        statAddPressed = true;
                    }
                    if (gp.ui.commandNum == 5){
                        //Reset
                        statAddPressed = true;
                    }
                    if (gp.ui.commandNum == 6){
                        //Back
                        statAddPressed = true;
                    }
                }
            }
    }
    public void playState(int code) {

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if (gp.player.attacking == false && shotKeyPressed == false){
                gp.gameState = gp.pauseState;
            }
        }
        if(code == KeyEvent.VK_C || code == KeyEvent.VK_B){
            if (gp.player.attacking == false && shotKeyPressed == false){
                gp.gameState = gp.characterState;
            }
        }
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_CONTROL){
            spacePressed = true;
            if (gp.player.currentWeapon.type == type_bow){
                weaponShotKeyPressed = true;
            }
        }
        if(code == KeyEvent.VK_Q) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

        // DEBUG
        if(code == KeyEvent.VK_T){
            if(showDebugText == false){
                showDebugText = true;
            }
            else if(showDebugText == true){
                showDebugText = false;
            }
        }

    }
    public void pauseState(int code) {

        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code) {

        if(code == KeyEvent.VK_SPACE) {
            gp.gameState = gp.playState;
        }

    }

    public void selectionState(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.selectedOption < 0){
                gp.ui.selectedOption = 5;
            }
            else {
                gp.ui.selectedOption--;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.selectedOption > 5){
                gp.ui.selectedOption = 0;
            }
            else {
                gp.ui.selectedOption++;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_CONTROL){
            statAddPressed = true;
        }
    }

    public void characterState(int code) {

        if(code == KeyEvent.VK_C || code == KeyEvent.VK_B) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_SPACE) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void optionsState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
            gp.ui.subState = 0;
            gp.ui.optionsCommandNum = 0;
        }
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_CONTROL) {
            spacePressed = true;
        }

        int maxOptionsCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxOptionsCommandNum = 5; break;
            case 1: maxOptionsCommandNum = 1; break;
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.optionsCommandNum--;
            gp.playSE(9);
            if (gp.ui.optionsCommandNum < 0) {
                gp.ui.optionsCommandNum = maxOptionsCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.optionsCommandNum++;
            gp.playSE(9);
            if (gp.ui.optionsCommandNum > maxOptionsCommandNum) {
                gp.ui.optionsCommandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.optionsCommandNum == 1 && gp.music.volumeScale > 0) {

                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.optionsCommandNum == 2 && gp.se.volumeScale > 0) {

                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.optionsCommandNum == 1 && gp.music.volumeScale < 5) {

                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.optionsCommandNum == 2 && gp.se.volumeScale < 5) {

                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }

    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_CONTROL) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
                gp.stopMusic();
                gp.playMusic(0);
            }
            else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
                gp.ui.commandNum = 0;
                gp.ui.resetTitleScreenStatsDistribution();
                gp.restart();
                gp.stopMusic();
                gp.playMusic(0);
            }
        }
    }

    public void tradeState(int code) {

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }

        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
                gp.ui.pageReset = false;

            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
                gp.ui.pageReset = false;
            }
        }
    }

    public void playerInventory(int code) {

        if(code == KeyEvent.VK_W) {
            if(gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_E) {
            if (gp.ui.playerSlotPage >= (gp.player.inventory.size()/20)){
                gp.ui.playerSlotPage = (gp.player.inventory.size()/20);
            }
            else {
                gp.ui.playerSlotPage++;
            }
        }
        if(code == KeyEvent.VK_Q) {
            if(gp.ui.playerSlotPage <= 0){
                gp.ui.playerSlotPage = 0;
            }
            else {
                gp.ui.playerSlotPage--;
            }
        }
    }

    public void npcInventory(int code) {

        if(code == KeyEvent.VK_W) {
            if(gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_E) {
            if (gp.ui.npcSlotPage >= (gp.ui.npc.inventory.size()/20)){
                gp.ui.npcSlotPage = (gp.ui.npc.inventory.size()/20);
            }
            else {
                gp.ui.npcSlotPage++;
            }
        }
        if(code == KeyEvent.VK_Q) {
            if(gp.ui.npcSlotPage <= 0){
                gp.ui.npcSlotPage = 0;
            }
            else {
                gp.ui.npcSlotPage--;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_CONTROL){
            spacePressed = false;
        }
        if(code == KeyEvent.VK_Q){
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_CONTROL){
            statAddPressed = false;
        }
    }
}
