package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Arrow_Night extends Projectile {

    GamePanel gp;
    public OBJ_Arrow_Night(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Night Arrow";
        type = type_arrow;
        ammoType = ammoTypeNightArrow;
        speed = 8;
        maxLife = 150;
        life = maxLife;
        attackValue = 4;
        useCost = 1;
        alive = false;
        ammo = 50;
        price = 30;
        description = "[" + name + "]\nArrows forged from night stone\nAmmo: " + ammo + "\nAttack Value: " + attackValue;
        getImage();
    }

    public void getImage() {

        up1 = setup("projectile/night_arrow_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/night_arrow_up_1", gp.tileSize, gp.tileSize);
        down1 = setup("projectile/night_arrow_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/night_arrow_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("projectile/night_arrow_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/night_arrow_left_1", gp.tileSize, gp.tileSize);
        right1 = setup("projectile/night_arrow_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/night_arrow_right_1", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {

        boolean haveResource = false;
        if (user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getParticleColor() {

        Color color = new Color(52, 84, 252);
        return color;
    }

    public int getParticleSize() {

        int size = 10; // px
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

