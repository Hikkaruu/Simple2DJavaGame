package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_HealOrb extends InteractiveTile{

    GamePanel gp;

    public IT_HealOrb(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/heal_orb", gp.tileSize, gp.tileSize);
        destructible = false;
    }


    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = true;
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(2);
    }

    public InteractiveTile getDestroyedForm() {

        InteractiveTile tile = null;
        return tile;
    }


}
