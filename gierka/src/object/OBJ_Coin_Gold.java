package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Gold extends Entity {

    GamePanel gp;

    public OBJ_Coin_Gold(GamePanel gp, int goldValue) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Gold";
        value = goldValue;
        down1 = setup("objects/coin", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {

        gp.playSE(2);
        gp.ui.addMessage("Gold +" + value);
        gp.player.coin += value;
    }
}
        