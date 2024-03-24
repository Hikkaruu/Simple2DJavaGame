package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Arrow_Normal extends Projectile {

    GamePanel gp;
    public OBJ_Arrow_Normal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Normal Arrow";
        type = type_arrow;
        ammoType = ammoTypeNormalArrow;
        speed = 7;
        maxLife = 280;
        life = maxLife;
        attackValue = 2;
        useCost = 1;
        alive = false;
        ammo = 100;
        price = 10;
        description = "[" + name + "]\nStandard quality arrows\nAmmo: " + ammo + "\nAttack Value: " + attackValue;
        getImage();
    }

    public void getImage() {

        up1 = setup("projectile/arrow_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/arrow_up_1", gp.tileSize, gp.tileSize);
        down1 = setup("projectile/arrow_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/arrow_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("projectile/arrow_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/arrow_left_1", gp.tileSize, gp.tileSize);
        right1 = setup("projectile/arrow_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/arrow_right_1", gp.tileSize, gp.tileSize);
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

        Color color = new Color(116, 108, 108);
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

