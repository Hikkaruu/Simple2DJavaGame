package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_Barrel extends InteractiveTile{

    GamePanel gp;

    public IT_Barrel(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/barrel", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_sword || entity.currentWeapon.type == type_axe)  {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(13);
    }

    public InteractiveTile getDestroyedForm() {

        InteractiveTile tile = new IT_Barrel_Destroyed(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {

        Color color = new Color(65, 50, 30);
        return color;
    }

    public int getParticleSize() {

        int size = 6; // px
        return size;
    }

    public int getParticleSpeed() {

        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {

        int maxLife = 20;
        return maxLife;
    }

}
