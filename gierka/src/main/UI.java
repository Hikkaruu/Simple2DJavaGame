package main;

import entity.Entity;
import object.OBJ_Coin_Gold;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaBold;
    BufferedImage bgimage, goImage;
    public boolean messageOn = false;
    //public String message = "";
    //int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int optionsCommandNum = 0;
    public int titleScreenState = 0; // 0: pierwszy ekran, 1: drugi ekran
    public boolean bgLoaded = false;
    public boolean goLoaded = false;
    public BufferedImage imageOptions;
    BufferedImage coin;
    public int endGameCounter = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int playerSlotPage = 0;
    public int npcSlotPage = 0;
    public int selectedOption = 0;
    int howManyStr = 0, howManyDex = 0, howManyInt = 0, howManyEnd = 0, howManyStatPoints = 0;
    int subState = 0;
    int counter = 0;
    public boolean pageReset = false;
    public Entity npc;


    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("font/Purisa Bold.ttf"));
                purisaBold = Font.createFont(Font.TRUETYPE_FONT, is);
                is = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("font/maruMonica.ttf"));
                maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Entity goldCoin = new OBJ_Coin_Gold(gp, 1);
        coin = goldCoin.down1;
    }

    public void addMessage (String text){

        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){

        this.g2 = g2;

        if (gameFinished == true) {

            endGameCounter++;
            String text;
            int x, y, textLength;

            g2.setFont(maruMonica.deriveFont(140F));
            g2.setColor(Color.yellow);
            text = "You Win!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2;
            g2.drawString(text, x, y);
            gp.player.collisionOn = true;
            if (endGameCounter > 300){
                gp.player.collisionOn = false;
                gp.gameState = gp.titleState;
                titleScreenState = 0;
                gp.restart();
                gp.currentMap = 0;
                gp.player.setDefaultPositions();
                gameFinished = false;
            }

        }
        else {
            //g2.setFont(maruMonica);
            g2.setFont(purisaBold);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Color.white);

            // Status ekranu glownego
            if (gp.gameState == gp.titleState){
                drawTitleScreen();
            }
            // Status gry
            if (gp.gameState == gp.playState){
                drawPlayerLife();
                if(gp.keyH.showGameInfo == true){
                    drawMessage();
                }
            }
            // Status pauzy
            if (gp.gameState == gp.pauseState){
                drawPlayerLife();
                drawPauseScreen();
            }
            // Status dialogu
            if (gp.gameState == gp.dialogueState){
                drawPlayerLife();
                drawDialogueScreen();
            }
            // Status postaci
            if (gp.gameState == gp.characterState){
                drawCharacterScreen();
                drawInventory(gp.player, true, true);
            }
            // Lvl Up
            if (gp.gameState == gp.lvlUpState){
                drawLvlUpScreen();
            }
            // Opcje
            if (gp.gameState == gp.optionsState) {
                drawOptionsScreen();
            }
            // Game over
            if (gp.gameState == gp.gameOverState) {
                drawGameOverScreen();
            }
            // Przejscie miedzy mapami
            if (gp.gameState == gp.transitionState) {
                drawTransition();
            }
            // Trade
            if (gp.gameState == gp.tradeState) {
                drawTradeScreen();
            }
        }
    }

    public void drawPlayerLife() {

        // HP

        //Test
        //gp.player.maxLife = 10;
        //gp.player.life = 10;

        if (gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        if (gp.player.life <= 0){
            gp.player.life = 0;
        }
        int x = gp.tileSize*4;
        int y = gp.tileSize/2;
        double oneScale = (double)(gp.tileSize*4)/gp.player.maxLife;
        double hpBarValue = oneScale*gp.player.life;
        String hpString;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect((gp.tileSize/2)-2, (gp.tileSize/2)-2, x+4, y+4);
        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(gp.tileSize/2, gp.tileSize/2, (int)hpBarValue, y);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
        //g2.setFont(maruMonica.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        hpString = Integer.toString(gp.player.life) + "/" + Integer.toString(gp.player.maxLife);
        if (gp.player.maxLife >= 0  && gp.player.maxLife < 10) {g2.drawString(hpString, gp.tileSize*2+8, gp.tileSize/2+24);}
        else if (gp.player.maxLife >= 10 && gp.player.maxLife < 20) {g2.drawString(hpString, gp.tileSize*2, gp.tileSize/2+24);}
        else if (gp.player.maxLife >= 20 && gp.player.maxLife < 30) {g2.drawString(hpString, gp.tileSize*2-12, gp.tileSize/2+24);}
        else if (gp.player.maxLife >= 30 && gp.player.maxLife < 100) {g2.drawString(hpString, gp.tileSize*2-6, gp.tileSize/2+24);}
        else if (gp.player.maxLife >= 100 && gp.player.maxLife < 200) {g2.drawString(hpString, gp.tileSize*2-16, gp.tileSize/2+24);}
        else if (gp.player.maxLife >= 200 && gp.player.maxLife < 300) {g2.drawString(hpString, gp.tileSize*2-26, gp.tileSize/2+24);}
        else if (gp.player.maxLife >= 300 && gp.player.maxLife < 1000) {g2.drawString(hpString, gp.tileSize*2-22, gp.tileSize/2+24);}

        // Mana

        if (gp.player.mana > gp.player.maxMana){
            gp.player.mana = gp.player.maxMana;
        }
        if (gp.player.mana <= 0){
            gp.player.mana = 0;
        }
        x = gp.tileSize*4;
        y = gp.tileSize/2;
        double oneScaleMana = (double)(gp.tileSize*4)/gp.player.maxMana;
        double mpBarValue = oneScaleMana*gp.player.mana;
        String mpString;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect((gp.tileSize/2)-2, (gp.tileSize)-2, x+4, y+4);
        g2.setColor(new Color(10, 120, 255));
        g2.fillRect(gp.tileSize/2, gp.tileSize, (int)mpBarValue, y);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
        //g2.setFont(maruMonica.deriveFont(Font.BOLD, 24F));
        g2.setColor(Color.white);
        mpString = Integer.toString(gp.player.mana) + "/" + Integer.toString(gp.player.maxMana);
        if (gp.player.maxMana >= 0  && gp.player.maxMana < 10) {g2.drawString(mpString, gp.tileSize*2+8, gp.tileSize+24);}
        else if (gp.player.maxMana >= 10 && gp.player.maxMana < 20) {g2.drawString(mpString, gp.tileSize*2, gp.tileSize+24);}
        else if (gp.player.maxMana >= 20 && gp.player.maxMana < 30) {g2.drawString(mpString, gp.tileSize*2-12, gp.tileSize+24);}
        else if (gp.player.maxMana >= 30 && gp.player.maxMana < 100) {g2.drawString(mpString, gp.tileSize*2-6, gp.tileSize+24);}
        else if (gp.player.maxMana >= 100 && gp.player.maxMana < 200) {g2.drawString(mpString, gp.tileSize*2-16, gp.tileSize+24);}
        else if (gp.player.maxMana >= 200 && gp.player.maxMana < 300) {g2.drawString(mpString, gp.tileSize*2-26, gp.tileSize+24);}
        else if (gp.player.maxMana >= 300 && gp.player.maxMana < 1000) {g2.drawString(mpString, gp.tileSize*2-22, gp.tileSize+24);}

        // Ammo
        if (gp.player.currentWeapon.type == gp.player.type_bow){
            String ammoString;
            ammoString = "Arrows: " + Integer.toString(gp.player.ammo);
            g2.setColor(new Color(35, 35, 35));
            g2.drawString(ammoString, (gp.tileSize+2)+12, gp.tileSize*2);
            g2.setColor(new Color(255, 255, 255));
            g2.drawString(ammoString, (gp.tileSize+2)+12, gp.tileSize*2+2);
        }
    }
    public void drawMessage() {

            int messageX = gp.tileSize;
            int messageY = gp.tileSize * 4;
            g2.setFont(maruMonica.deriveFont(Font.BOLD, 32F));

            for (int i = 0; i < message.size(); i++) {

                if (message.get(i) != null) {

                    g2.setColor(Color.black);
                    g2.drawString(message.get(i), messageX, messageY + 4);
                    g2.setColor(Color.white);
                    g2.drawString(message.get(i), messageX, messageY);

                    int counter = messageCounter.get(i) + 1; // messageCounter++
                    messageCounter.set(i, counter); // ustawienie licznika
                    messageY += 50;

                    if (messageCounter.get(i) > 180) {
                        message.remove(i);
                        messageCounter.remove(i);
                    }
                }
            }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {

            if (bgLoaded == false) {
                try {
                    bgimage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("background/main_screen_bg.jpg")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Tytul
            Color outlineColor = new Color(212, 175, 55);
            Color fillColor = Color.black;
            BasicStroke outlineStroke = new BasicStroke(4.0f);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Gierka";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            Color originalColor = g2.getColor();
            Stroke originalStroke = g2.getStroke();
            g2.setColor(Color.BLACK);
            RenderingHints originalHints = g2.getRenderingHints();
            GlyphVector glyphVector = g2.getFont().createGlyphVector(g2.getFontRenderContext(), text);
            Shape textShape = glyphVector.getOutline();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g2.drawImage(bgimage, 0, 0, gp.screenWidth, gp.screenHeight, null);

            g2.setColor(outlineColor);
            g2.setStroke(outlineStroke);
            g2.translate(x, y);
            g2.draw(textShape); // kolor obramowki
            g2.setColor(fillColor);
            g2.fill(textShape); // kolor srodka

            // cofniecie ustawien
            //g2.setColor(originalColor);
            //g2.setStroke(originalStroke);
            //g2.setRenderingHints(originalHints);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

            if (bgLoaded == false) {
                try {
                    imageOptions = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("background/raven.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            text = "NEW GAME";
            glyphVector = g2.getFont().createGlyphVector(g2.getFontRenderContext(), text);
            textShape = glyphVector.getOutline();
            g2.setColor(outlineColor);
            g2.setStroke(outlineStroke);
            x = getXforCenteredText(text);
            y = gp.tileSize * 4;
            g2.translate(52, 300);
            g2.draw(textShape); // kolor obramowki
            g2.setColor(fillColor);
            g2.fill(textShape); // kolor srodka
            if (commandNum == 0) {
                g2.drawImage(imageOptions, -50, -40, 45, 45, null);
            }

            text = "LOAD GAME";
            glyphVector = g2.getFont().createGlyphVector(g2.getFontRenderContext(), text);
            textShape = glyphVector.getOutline();
            g2.setColor(outlineColor);
            g2.setStroke(outlineStroke);
            g2.translate(-10, 60);
            g2.draw(textShape); // kolor obramowki
            g2.setColor(fillColor);
            g2.fill(textShape); // kolor srodka
            if (commandNum == 1) {
                g2.drawImage(imageOptions, -50, -40, 45, 45, null);
            }

            text = "QUIT";
            glyphVector = g2.getFont().createGlyphVector(g2.getFontRenderContext(), text);
            textShape = glyphVector.getOutline();
            g2.setColor(outlineColor);
            g2.setStroke(outlineStroke);
            g2.translate(70, 60);
            g2.draw(textShape); // kolor obramowki
            g2.setColor(fillColor);
            g2.fill(textShape); // kolor srodka
            if (commandNum == 2){
                g2.drawImage(imageOptions, -50, -40, 45, 45, null);
                }

            bgLoaded = true; // aby nie powtarzalo wczytania background'a
        }
        else if(titleScreenState == 1){

            // Ekran rozdania początkowych punktów
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Spend your skill points!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*2+16;
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(36F));

            text = "Strength";
            x = getXforCenteredText(text)/2;
            y += gp.tileSize*2+16;
            g2.drawString(text, x, y);
            if (howManyStr > 0){
                g2.setColor(new Color(229, 205, 28));
                g2.drawString("+" + howManyStr, x+200, y);
                g2.setColor(Color.white);
            }
            if (commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 0 && gp.keyH.statAddPressed == true && gp.player.statPoints > 0) {

                gp.player.strength++;
                gp.player.statPoints--;
                howManyStr++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }

            text = "Dexterity";
            x = getXforCenteredText(text)/2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (howManyDex > 0){
                g2.setColor(new Color(229, 205, 28));
                g2.drawString("+" + howManyDex, x+215, y);
                g2.setColor(Color.white);
            }
            if (commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 1 && gp.keyH.statAddPressed == true && gp.player.statPoints > 0) {

                gp.player.dexterity++;
                gp.player.statPoints--;
                howManyDex++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }

            text = "Intelligence";
            x = getXforCenteredText(text)/2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (howManyInt > 0){
                g2.setColor(new Color(229, 205, 28));
                g2.drawString("+" + howManyInt, x+265, y);
                g2.setColor(Color.white);
            }
            if (commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 2 && gp.keyH.statAddPressed == true && gp.player.statPoints > 0) {

                gp.player.inteligence++;
                gp.player.statPoints--;
                howManyInt++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }

            text = "Skill Points";
            x = getXforCenteredText(text) + 200;
            g2.drawString(text, x, y-100);


            g2.setFont(g2.getFont().deriveFont(64F));
            g2.setColor(new Color(229, 205, 28));
            text = Integer.toString(gp.player.statPoints);
            x = getXforCenteredText(text) + 200;
            g2.drawString(text, x, y-20);
            g2.setColor(Color.white);

            g2.setFont(g2.getFont().deriveFont(36F));

            text = "Endurance";
            x = getXforCenteredText(text)/2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (howManyEnd > 0){
                g2.setColor(new Color(229, 205, 28));
                g2.drawString("+" + howManyEnd, x+227, y);
                g2.setColor(Color.white);
            }
            if (commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 3 && gp.keyH.statAddPressed == true && gp.player.statPoints > 0) {

                gp.player.endurance++;
                gp.player.statPoints--;
                howManyEnd++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }
            y += gp.tileSize/2;

            text = "Accept";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 4) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 4 && gp.keyH.statAddPressed == true && gp.player.statPoints == 0) {

                gp.gameState = gp.playState;
                gp.keyH.statAddPressed = false;
                gp.player.attack = gp.player.getAttack();
                gp.player.defense = gp.player.getDefense();
                gp.player.maxMana += howManyInt;
                gp.player.mana = gp.player.maxMana;
                commandNum = 0;
                howManyStr = 0;
                howManyDex = 0;
                howManyInt = 0;
                howManyEnd = 0;
                howManyStatPoints = 0;
                //gp.stopMusic();
                //gp.playMusic(0);
            }

            text = "Reset";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 5) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 5 && gp.keyH.statAddPressed == true) {

                gp.player.statPoints += howManyStatPoints;
                gp.player.strength -= howManyStr;
                gp.player.dexterity -= howManyDex;
                gp.player.inteligence -= howManyInt;
                gp.player.maxMana -= howManyInt;
                gp.player.endurance -= howManyEnd;
                gp.player.mana = gp.player.maxMana;
                howManyStr = 0;
                howManyDex = 0;
                howManyInt = 0;
                howManyEnd = 0;
                howManyStatPoints = 0;
                gp.keyH.statAddPressed = false;
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 6) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            if (commandNum == 6 && gp.keyH.statAddPressed == true) {

                gp.player.statPoints += howManyStatPoints;
                gp.player.strength -= howManyStr;
                gp.player.dexterity -= howManyDex;
                gp.player.inteligence -= howManyInt;
                gp.player.maxMana -= howManyInt;
                gp.player.endurance -= howManyEnd;
                gp.player.mana = gp.player.maxMana;
                howManyStr = 0;
                howManyDex = 0;
                howManyInt = 0;
                howManyEnd = 0;
                howManyStatPoints = 0;
                gp.keyH.statAddPressed = false;
                gp.ui.titleScreenState = 0;
                gp.ui.commandNum = 0;
            }

        }
    }
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2 ;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        // Okienko
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawLvlUpScreen() {

        gp.player.invincible = true;
        int x = gp.tileSize*2;
        int y = gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4+20;
        drawSubWindow(x, y, width, height);

        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;

        int textX = frameX + 80;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 40;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));

        String stringValue;
        stringValue = "You advanced to lvl " + gp.player.level + "!";
        g2.drawString(stringValue, getXforCenteredText(stringValue), textY);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 34F));

        g2.drawString(Integer.toString(gp.player.statPoints), textX + 480, textY+105);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));

        g2.drawString("Stat points", textX + 420, textY+60);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

        if (selectedOption < 0){
            selectedOption = 5;
        }
        else if (selectedOption > 5){
            selectedOption = 0;
        }
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(textX + 380, textY + 138, 90, 40, 8, 8);
        g2.drawString("Accept", textX + 385, textY + 165);
        if (selectedOption == 4){
            g2.setColor(new Color(120, 170, 100));
            g2.fillRoundRect(textX + 380, textY + 138, 90, 40, 8, 8);
            g2.setColor(Color.white);
            g2.drawString("Accept", textX + 385, textY + 165);
            if (gp.keyH.statAddPressed == true && gp.player.statPoints == 0 && selectedOption == 4){

                howManyStr = 0;
                howManyDex = 0;
                howManyInt = 0;
                howManyEnd = 0;
                howManyStatPoints = 0;
                gp.player.getAttack();
                gp.player.getDefense();
                gp.keyH.statAddPressed = false;
                gp.gameState = gp.playState;
                gp.player.invincible = false;
                selectedOption = 0;
            }
        }
        g2.drawRoundRect(textX + 522, textY + 138, 90, 40, 8, 8);
        g2.drawString("Reset", textX + 535, textY + 165);
        if (selectedOption == 5){
            g2.setColor(new Color(170, 80, 80));
            g2.fillRoundRect(textX + 522, textY + 138, 90, 40, 8, 8);
            g2.setColor(Color.white);
            g2.drawString("Reset", textX + 535, textY + 165);
            if (gp.keyH.statAddPressed == true && selectedOption == 5){

                resetTitleScreenStatsDistribution();
            }
        }
        textY += lineHeight+10;
        g2.drawString("Strength: ", textX, textY);
        if (selectedOption == 0){
            g2.drawString("<-", textX + 210, textY-2);
            if (gp.keyH.statAddPressed == true && gp.player.statPoints > 0 && selectedOption == 0){
                gp.player.strength++;
                gp.player.statPoints--;
                howManyStr++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }
        }
        textY += lineHeight;
        g2.drawString("Dexterity: ", textX, textY);
        if (selectedOption == 1){
            g2.drawString("<-", textX + 210, textY-2);
            if (gp.keyH.statAddPressed == true && gp.player.statPoints > 0 && selectedOption == 1){
                gp.player.dexterity++;
                gp.player.statPoints--;
                howManyDex++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }
        }
        textY += lineHeight;
        g2.drawString("Inteligence: ", textX, textY);
        if (selectedOption == 2){
            g2.drawString("<-", textX + 210, textY-2);
            if (gp.keyH.statAddPressed == true && gp.player.statPoints > 0 && selectedOption == 2){
                gp.player.inteligence++;
                gp.player.maxMana++;
                gp.player.statPoints--;
                howManyInt++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }
        }
        textY += lineHeight;
        g2.drawString("Endurance: ", textX, textY);
        if (selectedOption == 3){
            g2.drawString("<-", textX + 210, textY-2);
            if (gp.keyH.statAddPressed == true && gp.player.statPoints > 0 && selectedOption == 3){
                gp.player.endurance++;
                gp.player.statPoints--;
                howManyEnd++;
                howManyStatPoints++;
                gp.keyH.statAddPressed = false;
            }
        }



        int tailX = (frameX + frameWidth) - 80;
        textY = frameY + gp.tileSize;
        String value;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));

        textY += lineHeight+10;
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.inteligence);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.endurance);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

    }

    public void resetTitleScreenStatsDistribution() {

        gp.player.statPoints += howManyStatPoints;
        gp.player.strength -= howManyStr;
        gp.player.dexterity -= howManyDex;
        gp.player.inteligence -= howManyInt;
        gp.player.maxMana -= howManyInt;
        gp.player.endurance -= howManyEnd;
        gp.player.mana = gp.player.maxMana;
        howManyStr = 0;
        howManyDex = 0;
        howManyInt = 0;
        howManyEnd = 0;
        howManyStatPoints = 0;
        gp.keyH.statAddPressed = false;
    }
    public void drawCharacterScreen() {

        // Parametry okienka
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Tekst
        g2.setColor(Color.white);
        g2.setFont(maruMonica.deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 40;

        // Parametry
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Health", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Inteligence", textX, textY);
        textY += lineHeight;
        g2.drawString("Endurance", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold", textX, textY);
        textY += lineHeight + 30;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 30;
        if (gp.player.currentShield.type == gp.player.type_null && gp.player.currentWeapon.type == gp.player.type_bow){

            g2.drawString("Ammunition", textX, textY);
        }
        else if (gp.player.currentWeapon.type != gp.player.type_bow){

            g2.drawString("Shield", textX, textY);
        }

        // Wartosci
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.inteligence);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.endurance);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp + "/" + gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight + 18;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-30, null);
        textY += lineHeight + 28;

        if (gp.player.currentShield.type == gp.player.type_null && gp.player.currentWeapon.type == gp.player.type_bow){

            g2.drawImage(gp.player.currentAmmo.down1, tailX - gp.tileSize, textY-30, null);
        }
        else if (gp.player.currentWeapon.type != gp.player.type_bow){
            g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-30, null);
        }




    }

    public void drawInventory(Entity entity, boolean cursor, boolean isPlayer) {

        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*5+52;
        int frameHeight = gp.tileSize*4+78;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player) {
            frameX = gp.tileSize*9;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*5+52;
            frameHeight = gp.tileSize*4+78;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*5+52;
            frameHeight = gp.tileSize*4+78;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // Okienko

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        String pageCurrentMax = gp.ui.playerSlotPage+1 + "/" + ((gp.player.inventory.size()/20)+1);
        g2.drawString(pageCurrentMax, frameWidth*2-4, frameY*6-6);


        // Sloty
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        // Itemy
        if (isPlayer == true){
            int pageStart = 0;
            int pageEnd = 0;
            int pageCounter = 0;
            int pageSlotChanger = 0;
            int forSecondArgument = 20;
            int forFirstArgument = 0;

            for(int i = 0; i < entity.inventory.size(); i++) {

                if(i == (forSecondArgument+pageEnd)){
                    pageStart += 20;
                    pageEnd += 20;
                    pageSlotChanger += 20;
                    pageCounter++;
                }

                if (i >= forFirstArgument+pageStart && i < forSecondArgument+pageEnd && playerSlotPage == pageCounter) {

                    // Podswietlanie zalozonych itemow
                    if(entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield ||
                            entity.inventory.get(i) == entity.currentAmmo){

                        g2.setColor(new Color(240, 180, 80));
                        g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                    }

                    g2.drawImage(entity.inventory.get(i).down1, slotX, slotY-2, null);
                    slotX += slotSize;


                    if (i == 4+pageSlotChanger || i == 9+pageSlotChanger || i == 14+pageSlotChanger) {
                        slotX = slotXstart;
                        if (i == 4+pageSlotChanger){
                            slotY += gp.tileSize+2;
                        }
                        if (i == 9+pageSlotChanger){
                            slotY += gp.tileSize+3;
                        }
                        if (i == 14+pageSlotChanger){
                            slotY += gp.tileSize+4;
                        }
                    }
                }
            }
        }
        else if (isPlayer == false) {

            int pageStart = 0;
            int pageEnd = 0;
            int pageCounter = 0;
            int pageSlotChanger = 0;
            int forSecondArgument = 20;
            int forFirstArgument = 0;

            for(int i = 0; i < entity.inventory.size(); i++) {

                if(i == (forSecondArgument+pageEnd)){
                    pageStart += 20;
                    pageEnd += 20;
                    pageSlotChanger += 20;
                    pageCounter++;
                }

                if (i >= forFirstArgument+pageStart && i < forSecondArgument+pageEnd && npcSlotPage == pageCounter) {

                    g2.drawImage(entity.inventory.get(i).down1, slotX, slotY-2, null);
                    slotX += slotSize;


                    if (i == 4+pageSlotChanger || i == 9+pageSlotChanger || i == 14+pageSlotChanger) {
                        slotX = slotXstart;
                        if (i == 4+pageSlotChanger){
                            slotY += gp.tileSize+2;
                        }
                        if (i == 9+pageSlotChanger){
                            slotY += gp.tileSize+3;
                        }
                        if (i == 14+pageSlotChanger){
                            slotY += gp.tileSize+4;
                        }
                    }
                }
            }
        }


        // Kursor
        if (cursor == true) {

            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // Opis itemów - okienko
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*4;

            // Opis itemów - tekst
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize-16;
            g2.setFont(maruMonica.deriveFont(28F));

            int itemIndex = 0;

            if (isPlayer == true) {
                itemIndex = getItemIndexOnSlot(slotCol, slotRow) + (20*playerSlotPage);
            }
            else if (isPlayer == false) {
                itemIndex = getItemIndexOnSlot(slotCol, slotRow) + (20*npcSlotPage);
            }


            if (itemIndex < entity.inventory.size()){

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                if (entity.inventory.get(itemIndex) != null){
                    for(String line: entity.inventory.get(itemIndex).description.split("\n")){

                        g2.drawString(line, textX, textY);
                        textY += 32;
                    }
                }
            }
        }
    }

    public void drawGameOverScreen() {


        if (goLoaded == false) {
            try {
                goImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("background/gameover.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g2.setColor(new Color(0, 0, 0, 240));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 110f));

        text = "You are dead";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        g2.drawImage(goImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setColor(new Color(255, 255, 255, 220));
        g2.drawString(text, x-4, y-4);

        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
        goLoaded = true;
    }

    public void drawOptionsScreen(){

        g2.setColor(Color.white);
        g2.setFont(maruMonica.deriveFont(36F));

        // Dodatkowe okienko
        int frameX = gp.tileSize*4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_endGameConfirmation(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
        }

        gp.keyH.spacePressed  = false;
    }

    public  void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        // Tytul
        g2.setFont(maruMonica.deriveFont(48F));
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(maruMonica.deriveFont(36F));
        // Game Info ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Game Info", textX, textY);
        if (optionsCommandNum == 0) {
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                if (gp.keyH.showGameInfo == false){
                    gp.keyH.showGameInfo = true;
                }
                else if (gp.keyH.showGameInfo == true){
                    gp.keyH.showGameInfo = false;
                }
            }
        }

        // Muzyka
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (optionsCommandNum == 1) {
            g2.drawString(">", textX-30, textY);
        }

        // Dźwięk
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (optionsCommandNum == 2) {
            g2.drawString(">", textX-30, textY);
        }

        // Kontrola
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (optionsCommandNum == 3) {
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                subState = 2;
                optionsCommandNum = 0;
            }
        }

        // Koniec Gry
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (optionsCommandNum == 4) {
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                subState = 1;
                optionsCommandNum = 0;
            }
        }

        // Back
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (optionsCommandNum == 5) {
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                gp.gameState = gp.playState;
                optionsCommandNum = 0;
            }
        }

        // Game info check box
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 36;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 32, 32);
        if (gp.keyH.showGameInfo == true) {
            g2.fillRect(textX, textY, 32, 32);
        }

        // Pasek poziomu głośności
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 160, 32); // 160/5 = 32
        int volumeWidth = 32 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 32);


        // Pasek głośności dźwięków
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 160, 32);
        volumeWidth = 32 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 32);

        gp.config.saveConfig();
    }

    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        // Tytuł
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY+=gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY+=gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY+=gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY+=gp.tileSize;
        g2.drawString("Pause", textX, textY); textY+=gp.tileSize;
        g2.drawString("Options", textX, textY); textY+=gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY+=gp.tileSize;
        g2.drawString("SPACE/CTRL", textX-50, textY); textY+=gp.tileSize;
        g2.drawString("Q", textX, textY); textY+=gp.tileSize;
        g2.drawString("C/B", textX, textY); textY+=gp.tileSize;
        g2.drawString("P", textX, textY); textY+=gp.tileSize;
        g2.drawString("ESC", textX, textY); textY+=gp.tileSize;

        // Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (optionsCommandNum == 0){
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                subState = 0;
                optionsCommandNum = 3;
            }
        }

    }

    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";

        g2.setFont(maruMonica.deriveFont(42F));
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        g2.setFont(maruMonica.deriveFont(36F));

        // Tak
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (optionsCommandNum == 0) {
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                subState = 0;
                // Reset wyboru
                gp.player.statPoints += howManyStatPoints;
                gp.player.strength -= howManyStr;
                gp.player.dexterity -= howManyDex;
                gp.player.inteligence -= howManyInt;
                gp.player.maxMana -= howManyInt;
                gp.player.endurance -= howManyEnd;
                gp.player.mana = gp.player.maxMana;
                howManyStr = 0;
                howManyDex = 0;
                howManyInt = 0;
                howManyEnd = 0;
                howManyStatPoints = 0;
                gp.keyH.statAddPressed = false;
                // Main screen
                gp.ui.titleScreenState = 0;
                gp.ui.commandNum = 0;
                gp.gameState = gp.titleState;
            }
        }

        // Nie
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (optionsCommandNum == 1) {
            g2.drawString(">", textX-30, textY);
            if (gp.keyH.spacePressed == true) {
                subState = 0;
                optionsCommandNum = 4;
            }
        }
    }

    public void drawTransition() {

        counter++;
        g2.setColor(new Color(0, 0, 0, counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;

        }

    }

    public void drawTradeScreen() {

        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.spacePressed = false;
    }

    public void trade_select() {

        drawDialogueScreen();

        // Okno
        int x = gp.tileSize * 12;
        int y = gp.tileSize * 4;
        int width = (int)(gp.tileSize * 2.5);
        int height = (gp.tileSize * 3) - 16;
        drawSubWindow(x, y, width, height);

        // Tekst
        x += gp.tileSize-16;
        y += gp.tileSize-16;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.spacePressed == true) {
                subState = 1;
            }
        }

        y += gp.tileSize-16;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.spacePressed == true) {
                subState = 2;
            }
        }

        y += gp.tileSize-16;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.spacePressed == true) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "See you again.";
            }
        }


    }

    public void trade_buy() {

        if (pageReset == false) {
            playerSlotPage = gp.player.inventory.size()/20;
            npcSlotPage = 0;
            pageReset = true;
        }

        // nasze eq
        drawInventory(gp.player, false, true);
        // eq npc
        drawInventory(npc, true, false);

        Font prevFont = g2.getFont();

        // player pages
        g2.setFont(maruMonica.deriveFont(32F));
        String pageCurrentMax = gp.ui.playerSlotPage+1 + "/" + ((gp.player.inventory.size()/20)+1);
        g2.drawString(pageCurrentMax, gp.tileSize*11+32, gp.tileSize*6-4);

        // npc pages
        pageCurrentMax = gp.ui.npcSlotPage+1 + "/" + ((gp.ui.npc.inventory.size()/20)+1);
        g2.drawString(pageCurrentMax, gp.tileSize*4+16, gp.tileSize*6-4);

        g2.setFont(prevFont);
        int x = gp.tileSize*2;
        int y = gp.tileSize*10+12;
        int width = gp.tileSize*5+52;
        int height = (int)(gp.tileSize*1.5);
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+30, y+52);

        // Gold
        x = gp.tileSize*9;
        y = gp.tileSize*10+12;
        width = gp.tileSize*5+52;
        height = (int)(gp.tileSize*1.5);
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Gold: " + gp.player.coin, x+30, y+52);

        // Cena
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow) + (20*npcSlotPage); ;
        if (itemIndex < npc.inventory.size()) {

            x = gp.tileSize*5+20;
            y = gp.tileSize*5+45;
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 48, 48, null);

            g2.setFont(maruMonica.deriveFont(36F));
            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize*7+32);
            g2.drawString(text, x, y+45);

            // Kupowanie itemów
            if (gp.keyH.spacePressed == true) {
                if (npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Not enough gold!";
                    drawDialogueScreen();
                }
                else {
                    gp.player.coin -= npc.inventory.get(itemIndex).price;
                    gp.player.inventory.add(npc.inventory.get(itemIndex));
                    if (gp.player.inventory.size()/20 > playerSlotPage){
                        playerSlotPage++;
                    }
                }
            }
        }
    }

    public void trade_sell() {

        if (pageReset == false) {
            playerSlotPage = 0;
            npcSlotPage = 0;
            pageReset = true;
        }

        drawInventory(gp.player, true, true);

        // Strony eq
        Font prevFont = g2.getFont();
        g2.setFont(maruMonica.deriveFont(32F));
        String pageCurrentMax = gp.ui.playerSlotPage+1 + "/" + ((gp.player.inventory.size()/20)+1);
        g2.drawString(pageCurrentMax, gp.tileSize*11+16, gp.tileSize*6-4);
        g2.setFont(prevFont);

        int x;
        int y;
        int width;
        int height;

        x = gp.tileSize*2;
        y = gp.tileSize*10+12;
        width = gp.tileSize*5+52;
        height = (int)(gp.tileSize*1.5);
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+30, y+52);

        // Gold
        x = gp.tileSize*9;
        y = gp.tileSize*10+12;
        width = gp.tileSize*5+52;
        height = (int)(gp.tileSize*1.5);
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Gold: " + gp.player.coin, x+30, y+52);


        // Cena
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow) + (20*playerSlotPage) ;
        if (itemIndex < gp.player.inventory.size()) {

            x = gp.tileSize*12+20;
            y = gp.tileSize*5+45;
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 48, 48, null);

            g2.setFont(maruMonica.deriveFont(36F));
            int price = (int)(gp.player.inventory.get(itemIndex).price*0.6);
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize*14+32);
            g2.drawString(text, x, y+45);

            // Sprzedawanie itemów
            if (gp.keyH.spacePressed == true) {

                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You can't sell an equipped item!";
                }
                else {
                    gp.player.inventory.remove(itemIndex);
                    gp.player.coin += price;
                    if (gp.player.inventory.size()/20 < playerSlotPage){
                        playerSlotPage++;
                    }
                }
            }
        }

    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {

        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 215);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }

    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
