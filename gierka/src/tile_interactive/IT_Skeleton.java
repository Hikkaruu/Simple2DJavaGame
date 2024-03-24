package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Skeleton extends InteractiveTile {

    GamePanel gp;

    public IT_Skeleton(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/skeleton", gp.tileSize, gp.tileSize);
        destructible = false;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.height = 0;
        solidArea.width = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}