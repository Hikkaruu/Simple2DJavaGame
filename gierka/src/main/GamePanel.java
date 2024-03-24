package main;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // USTAWIENIA EKRANU
    final int originalTileSize = 16; // 16x16
    final int scale = 4;

    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1024px
    public final int screenHeight = tileSize * maxScreenRow; // 768px

    // USTAWIENIA ŚWIATA
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public final int maxMap = 50;
    public int currentMap = 0;

    //FPS'y
    int FPS = 60;


    // System
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    Thread gameThread;

    // Objekty i Entity
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][100];
    public Entity npc[][] = new Entity[maxMap][30];
    public Entity monster[][] = new Entity[maxMap][100];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    public PathFinder pFinder = new PathFinder(this);

    // Statusy gry
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int lvlUpState = 5;
    public final int optionsState = 6;
    public final int gameOverState = 7;
    public final int transitionState = 8;
    public final int tradeState = 9;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        //Muzyka w tle
        //playMusic(0);
        gameState = titleState;

    }

    public void retry() {

        eHandler.teleport(0, player.spawnX/tileSize, player.spawnY/tileSize);
        player.restoreLifeAndMana();
        aSetter.setNPC();
        aSetter.setMonster();
        //player.setDefaultPositions();
    }

    public void restart() {

        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // miliard nano sekund = 1sekunda, rysowanie co 0.01(6) sekundy
        double delta = 0;
        long timer = 0;
        int drawCount = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                // update'owanie
                update();
                // rysowanie
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
            }


        }
    }
    public void update(){

        if (gameState == playState){
            // PLAYER
            player.update();
            // NPC
            for (int i = 0; i < npc[1].length; i++){
                if (npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++){

                if(monster[currentMap][i] != null){
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }

                }
            }
            for (int i = 0; i < projectileList.size(); i++){

                if(projectileList.get(i) != null){
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++){

                if(particleList.get(i) != null){
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }

        }
        if (gameState == pauseState){
            // nic nie rob
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        long drawStart = 0;
        if (keyH.showDebugText == true){
            drawStart = System.nanoTime();
        }

        // Ekran glowny
        if (gameState == titleState){
            ui.draw(g2);
        }
        // Pozostale
        else {
            // Textury
            tileM.draw(g2);

            // Interaktywne textury
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // Gracz
            entityList.add(player);

            // Entity
            for (int i = 0; i < npc[1].length; i++){
                if (npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }

            // Objekty
            for (int i = 0; i < obj[1].length; i++){
                if (obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }

            // Moby
            for (int i = 0; i < monster[1].length; i++){
                if (monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }

            // Projectile
            for (int i = 0; i < projectileList.size(); i++){
                if (projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }

            // Particle
            for (int i = 0; i < particleList.size(); i++){
                if (particleList.get(i) != null){
                    entityList.add(particleList.get(i));
                }
            }

            // Sortowanie
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // Rysowanie entities
            for (int i = 0; i < entityList.size(); i++){

                entityList.get(i).draw(g2);
            }
            // Czyszczenie listy
            entityList.clear();


            // UI
            ui.draw(g2);
        }

        // DEBUG
        if(keyH.showDebugText == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.worldX, x, y); y+=lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y); y+=lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y); y+=lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y); y+=lineHeight;
            g2.drawString("Draw Time: "+ passed, x, y);
        }

        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){


    }
    public void playSE(int i){

        se.setFile(i);
        se.play();
    }
}
