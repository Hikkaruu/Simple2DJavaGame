package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Sword_Projectile extends Projectile {

    GamePanel gp;

    public OBJ_Sword_Projectile(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Sword Projectile";
        speed = 8;
        maxLife = 100;
        life = maxLife;
        attack = 12;
        useCost = 1;
        alive = false;
        type = type_spell;
        getImage();
    }

    public void getImage() {

        up1 = setup("projectile/four_swords_up", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/four_swords_up", gp.tileSize, gp.tileSize);
        down1 = setup("projectile/four_swords_down", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/four_swords_down", gp.tileSize, gp.tileSize);
        left1 = setup("projectile/four_swords_left", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/four_swords_left", gp.tileSize, gp.tileSize);
        right1 = setup("projectile/four_swords_right", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/four_swords_right", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {

        boolean haveResource = false;
        if (user.mana >= useCost) {
            haveResource = true;
        }
        return  haveResource;
    }

    public void subtractResource(Entity user) {

        user.mana -= useCost;
    }

    public Color getParticleColor() {

        Color color = new Color(83, 86, 90);
        return color;
    }

    public int getParticleSize() {

        int size = 12; // px
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

