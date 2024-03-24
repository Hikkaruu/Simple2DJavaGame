package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        type = type_spell;
        getImage();
    }

    public void getImage() {

        up1 = setup2("projectile/mel_skill_1", gp.tileSize, gp.tileSize);
        up2 = setup2("projectile/mel_skill_2", gp.tileSize, gp.tileSize);
        up3 = setup2("projectile/mel_skill_3", gp.tileSize, gp.tileSize);
        up4 = setup2("projectile/mel_skill_4", gp.tileSize, gp.tileSize);
        up5 = setup2("projectile/mel_skill_5", gp.tileSize, gp.tileSize);

        down1 = setup2("projectile/mel_skill_1", gp.tileSize, gp.tileSize);
        down2 = setup2("projectile/mel_skill_2", gp.tileSize, gp.tileSize);
        down3 = setup2("projectile/mel_skill_3", gp.tileSize, gp.tileSize);
        down4 = setup2("projectile/mel_skill_4", gp.tileSize, gp.tileSize);
        down5 = setup2("projectile/mel_skill_5", gp.tileSize, gp.tileSize);

        left1 = setup2("projectile/mel_skill_1", gp.tileSize, gp.tileSize);
        left2 = setup2("projectile/mel_skill_2", gp.tileSize, gp.tileSize);
        left3 = setup2("projectile/mel_skill_3", gp.tileSize, gp.tileSize);
        left4 = setup2("projectile/mel_skill_4", gp.tileSize, gp.tileSize);
        left5 = setup2("projectile/mel_skill_5", gp.tileSize, gp.tileSize);

        right1 = setup2("projectile/mel_skill_1", gp.tileSize, gp.tileSize);
        right2 = setup2("projectile/mel_skill_2", gp.tileSize, gp.tileSize);
        right3 = setup2("projectile/mel_skill_3", gp.tileSize, gp.tileSize);
        right4 = setup2("projectile/mel_skill_4", gp.tileSize, gp.tileSize);
        right5 = setup2("projectile/mel_skill_5", gp.tileSize, gp.tileSize);


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

        Color color = new Color(240, 50, 0);
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

