package creator.gameChar.monster;

import creator.gameChar.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public abstract class Monster extends Entity {

    protected int screenX;
    protected int screenY;
    private boolean locked = false;
    private final int timelockTime = 800;
    private int timelockTimeCount = timelockTime;
    private final int flickerTime = 60;
    private int flickerTimeCount = flickerTime;
    private boolean dead = false;
    private boolean getDamaged = false;
    private boolean faceBomb = false;
    protected Random random;
    public Monster(GamePanel gPanel, int x, int y, int hitboxDefaultX, int hitboxDefaultY,
                   int sprite, int speed, int direction, int life, int maxLife, int code) {
        super(gPanel, x, y, hitboxDefaultX, hitboxDefaultY,
                sprite, speed, direction, life, maxLife);
        this.code = code;
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz() - 8, gPanel.getActualTileSz() - 8);
        random = new Random();
    }

    protected boolean checkPosition(int x, int y, GamePanel gPanel) {
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    @Override
    public void update() {

        collided = false;
//        dodgeBomb();
        gPanel.getCollisionDectection().checkMapCollision(this);
        gPanel.getCollisionDectection().checkCollisionBomb(this);
        if(life > 0) gPanel.getCollisionDectection().checkMonsterCollidePlayer(this);
//        gPanel.getCollisionDectection().checkExplosionZone(this);

//        if (collided) {
//            direction = random.nextInt(4);
//        }
//        else {
//            if (direction == 0) y += speed;
//            else if (direction == 1) y -= speed;
//            else if (direction == 2) x -= speed;
//            else if (direction == 3) x += speed;
//        }

        if(locked) {
            timelockTimeCount--;
            if(timelockTimeCount == 0) {
                locked = false;
                timelockTimeCount = timelockTime;
            }
        }
        else {
            if (collided) {
                direction = random.nextInt(4);
            }
            else {
                if (direction == 0) y += speed;
                else if (direction == 1) y -= speed;
                else if (direction == 2) x -= speed;
                else if (direction == 3) x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 0) spriteNum = 1;
                else if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 0;
                spriteCounter = 0;
            }
        }
        checkMonsterDead();
//        if (blur) {
//            blurTimeCount--;
//            if(blurTimeCount == 0) {
//                blur = false;
//                blurTimeCount = blurTime;
//            }
//        }

//        spriteCounter++;
//        if (spriteCounter > 15) {
//            if (spriteNum == 0) spriteNum = 1;
//            else if (spriteNum == 1) spriteNum = 2;
//            else if (spriteNum == 2) spriteNum = 3;
//            else if (spriteNum == 3) spriteNum = 0;
//            spriteCounter = 0;
//        }
    }

    @Override
    public void renderImg(Graphics2D g2) {
        if(direction == 0) {
            img = down[spriteNum];
        }
        else if(direction == 1) {
            img = up[spriteNum];
        }
        else if(direction == 2) {
            img = left[spriteNum];
        }
        else if(direction == 3) {
            img = right[spriteNum];
        }
        // when get damaged by bomb, change direction
        if(getDamaged) {
            direction = gPanel.getPlayer().getDirection();
            getDamaged = false;
        }

        if(img != null) {
            screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
            screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
            if (checkPosition(x, y, gPanel)) {

                g2.setColor(Color.gray);
                g2.fillRect(screenX, screenY - 15, gPanel.getActualTileSz(), 5);
                g2.setColor(Color.red);
                g2.fillRect(screenX, screenY - 15, life*(gPanel.getActualTileSz()/maxLife), 5);
                if(life <= 0) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
                g2.drawImage(img, screenX, screenY, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            }
        }
    }

    private void checkMonsterDead() {
        if(life <= 0) {
            locked = true;
            flickerTimeCount--;
            if(flickerTimeCount == 0) {
                dead = true;
                flickerTimeCount = flickerTime;
            }
        }
    }

    private void dodgeBomb() {
        for (int i = 0; i < gPanel.bombList.size(); i++) {
            faceBomb = changeDirection(this.x,this.y,gPanel.bombList.get(i).getX(),gPanel.bombList.get(i).getY());
        }
    }

    private boolean changeDirection(int xM, int yM, int xB, int yB) {
//        System.out.println((int) Math.sqrt((xM-xB)*(xM-xB) + (yM-yB)*(yM-yB)) + "distance");
        if(Math.sqrt((xM-xB)*(xM-xB) + (yM-yB)*(yM-yB)) <= (1.5*gPanel.getActualTileSz())) {
            if(xM == xB) {
                if(yB > yM) direction = 0;
                else direction = 1;
            } else if(xB > xM) {
                direction = 2;
            } else {
                direction = 3;
            }
            return true;
//            if(xM == xB) {
//                if(yB > yM) direction = 0;
//                else direction = 1;
//            } else if(xB > xM) {
//                direction = 2;
//            } else {
//                direction = 3;
//            }
        }
        return false;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isDead() {
        return dead;
    }

    public void setGetDamaged(boolean getDamaged) {
        this.getDamaged = getDamaged;
    }
}
