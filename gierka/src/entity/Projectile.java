package entity;

import main.GamePanel;

import java.awt.*;

public class Projectile extends Entity {

    Entity user;
    public Rectangle projectileSolidArea = new Rectangle(32, 32, 8, 8);
    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

    }

    public void update() {

        if (user == gp.player) {

            collisionOn = false;
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkTileProjectile(this);
            if (monsterIndex != 999) {

                if (gp.player.projectile.type == type_spell && gp.player.projectileArrow.alive == false) {

                    gp.player.damageMonster(monsterIndex, attack + gp.player.inteligence);
                    generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                }
                if (gp.player.projectileArrow.type == type_arrow && gp.player.projectile.alive == false) {

                    gp.player.damageMonster(monsterIndex, gp.player.attack);
                    generateParticle(user.projectileArrow, gp.monster[gp.currentMap][monsterIndex]);
                }

                alive = false;
            }

        }
        if (user != gp.player) {

            collisionOn = false;
            gp.cChecker.checkTileProjectile(this);
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (gp.player.invincible == false && contactPlayer == true) {

                damagePlayer(user.attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
        }

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        if (collisionOn == true){
            alive = false;
        }

        life--;
        if (life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            }
            else if (spriteNum == 3) {
                spriteNum = 4;
            }
            else if (spriteNum == 4) {
                spriteNum = 5;
            }
            else if (spriteNum == 5) {
                spriteNum = 1;
            }
                spriteCounter = 0;
        }
    }

    public boolean haveResource(Entity user) {

        boolean haveResource = false;
        return haveResource;
    }

    public void subtractResource(Entity user) {

    }

}
