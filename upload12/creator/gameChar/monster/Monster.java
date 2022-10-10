package creator.gameChar.monster;

import creator.gameChar.Entity;
import creator.gameChar.monster.boss.Boss;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.util.Random;

public abstract class Monster extends Entity {

    protected int screenX;
    protected int screenY;
    protected int healthBarLength;
    private boolean locked = false;
    private final int timelockTime = 800;
    private int timelockTimeCount = timelockTime;
    private final int flickerTime = 60;
    private int flickerTimeCount = flickerTime;
    private final int dodgeBombTime = 60;
    private int dodgeBombTimeCount = dodgeBombTime;
    private boolean dead = false;
    private boolean getDamaged = false;
    private boolean onPath = false;
    private boolean chasing = onPath;

    protected Random random;
    public Monster(GamePanel gPanel, int x, int y, int hitboxDefaultX, int hitboxDefaultY,
                   int sprite, int speed, int direction, int life, int maxLife) {
        super(gPanel, x, y, hitboxDefaultX, hitboxDefaultY,
                sprite, speed, direction, life, maxLife);
        random = new Random();
    }

    protected boolean checkPosition(int x, int y, GamePanel gPanel) {
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    private void collisionCheck() {
        collided = false;
        gPanel.getCollisionDectection().checkMapCollision(this);
        gPanel.getCollisionDectection().checkCollisionBomb(this,gPanel.bombList);
        if(life > 0) gPanel.getCollisionDectection().checkMonsterCollidePlayer(this);
    }

    @Override
    public void update() {
        dodgeBomb();
        checkMonsterDead();
        collisionCheck();
//        gPanel.getCollisionDectection().checkExplosionZone(this);
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
            // when get damaged by bomb, change direction
            if(getDamaged) {
                direction = gPanel.getPlayer().getDirection();
                getDamaged = false;
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

//        System.out.println(collided + " cooc");
//        dodgeBomb();
//        if(chasing != onPath) {
//            if(direction == 0) direction = 1;
//            else if (direction == 1) direction = 0;
//            else if (direction == 2) direction = 3;
//            else if (direction == 3) direction = 2;
//        }
//        chasing = onPath;

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

        if(img != null) {
            screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
            screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
            if (checkPosition(x, y, gPanel)) {

                g2.setColor(Color.gray);
                g2.fillRect(screenX, screenY - 15, healthBarLength, 5);
                g2.setColor(Color.red);
                g2.fillRect(screenX, screenY - 15, life*(healthBarLength/maxLife), 5);
                if(life <= 0) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
                g2.drawImage(img, screenX, screenY, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            }
        }
    }

    private void checkMonsterDead() {
        if(life <= 0) {
            if(this instanceof Boss) gPanel.score += 5;
            else gPanel.score += 1;
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
            changeDirection(this.x,this.y,gPanel.bombList.get(i).getX(),gPanel.bombList.get(i).getY());
        }
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    private void changeDirection(int xM, int yM, int xB, int yB) {
//        System.out.println((int) Math.sqrt((xM-xB)*(xM-xB) + (yM-yB)*(yM-yB)) + "distance");
        if(distance(xM,yM,xB,yB) <= (2*gPanel.getActualTileSz())) {
            if(dodgeBombTimeCount == dodgeBombTime) {
                if(direction == 0) direction = 1;
                else if(direction == 1) direction = 0;
                else if(direction == 2) direction = 3;
                else if(direction == 3) direction = 2;
            }
        }
        dodgeBombTimeCount--;
        if(dodgeBombTimeCount == 0) {
            dodgeBombTimeCount = dodgeBombTime;
        }
    }

//    private void searchPath(int finishRow, int finishCol) {
//        int startRow = (this.y + this.hitbox.y)/gPanel.getActualTileSz();
//        int startCol = (this.x + this.hitbox.x)/gPanel.getActualTileSz();
////        System.out.println(startRow + " " + startCol);
//        gPanel.pathFinder.setNodes(startRow,startCol,finishRow,finishCol);
//        if(gPanel.pathFinder.search()) {
//            // next possible position
//            int nextLeftPosX = gPanel.pathFinder.pathList.get(0).col * gPanel.getActualTileSz();
//            int nextRightPosX = nextLeftPosX + gPanel.getActualTileSz();
//            int nextTopPosY = gPanel.pathFinder.pathList.get(0).row * gPanel.getActualTileSz();
//            int nextBotPosY = nextTopPosY + gPanel.getActualTileSz();
//            // monster hitbox current position
//            int curLeftPosX = this.x + this.hitbox.x;
//            int curRightPosX = curLeftPosX + this.hitbox.width;
//            int curTopPosY = this.y + this.hitbox.y;
//            int curBotPosY = curTopPosY + this.hitbox.height;
//
//            if(curTopPosY > nextTopPosY && curLeftPosX >= nextLeftPosX && curRightPosX <= nextRightPosX) {
//                direction = 1; // up
//            }
//            else if(curTopPosY < nextTopPosY && curLeftPosX >= nextLeftPosX && curRightPosX <= nextRightPosX) {
//                direction = 0; // down
//            }
//            else if(curTopPosY >= nextTopPosY && curBotPosY < nextBotPosY) {
//                if(curLeftPosX > nextLeftPosX) direction = 2; // left
//                else if (curLeftPosX < nextLeftPosX) direction = 3; // right
//            }
//            else if(curTopPosY > nextTopPosY && curLeftPosX > nextLeftPosX) {
//                direction = 1; // up
//                collisionCheck();
//                if(collided) direction = 2; // left
//            }
//            else if(curTopPosY > nextTopPosY && curLeftPosX < nextLeftPosX) {
//                direction = 1; // up
//                collisionCheck();
//                if(collided) direction = 3; // right
//            }
//            else if(curTopPosY < nextTopPosY && curLeftPosX > nextLeftPosX) {
//                direction = 0; // down
//                collisionCheck();
//                if(collided) direction = 2; // left
//            }
//            else if(curTopPosY < nextTopPosY && curLeftPosX < nextLeftPosX) {
//                direction = 0; // down
//                collisionCheck();
//                if(collided) direction = 3; // right
//            }
//
//        }
//
//    }

    private void trigger(int finishPosX, int finishPosY) {
        onPath = distance(this.x, this.y, finishPosX, finishPosY) <= 3 * gPanel.getActualTileSz();

    }
    /*
    private void findPath() {
        int startY = this.y + this.hitbox.y;
        int startX = this.x + this.hitbox.x;
        int finishY = gPanel.getPlayer().getY() + gPanel.getPlayer().getHitbox().y;
        int finishX = gPanel.getPlayer().getX() + gPanel.getPlayer().getHitbox().x;
        System.out.println(startY + " " + startX + " "
                + finishY + " " + finishX);
        if(startY < finishY) {
            direction = 0; // down
            gPanel.getCollisionDectection().checkMapCollision(this);
            gPanel.getCollisionDectection().checkCollisionBomb(this);
            if(collided) {
                direction = 2;
                gPanel.getCollisionDectection().checkMapCollision(this);
                gPanel.getCollisionDectection().checkCollisionBomb(this);
                if(collided) {
                    direction = 3;
                }
            }
        } else if(startY > finishY) {
            direction = 1; // up
            gPanel.getCollisionDectection().checkMapCollision(this);
            gPanel.getCollisionDectection().checkCollisionBomb(this);
            if(collided) {
                direction = 2;
                gPanel.getCollisionDectection().checkMapCollision(this);
                gPanel.getCollisionDectection().checkCollisionBomb(this);
                if(collided) {
                    direction = 3;
                }
            }
        } else {
            if(startX < finishX) {
                direction = 3; // right
            } else {
                direction = 2; // left
            }
        }

    } */
//    private int currentCol(int x) {
//        if(x % gPanel.getActualTileSz() == 0) return x /gPanel.getActualTileSz();
//        int posXRight = x + gPanel.getActualTileSz();
//        int curColPosX = (posXRight/gPanel.getActualTileSz()) * gPanel.getActualTileSz();
//        int distanceLeft = curColPosX - x;
//        int distanceRight = posXRight - curColPosX;
//        if(distanceLeft > distanceRight) return (curColPosX - gPanel.getActualTileSz())/gPanel.getActualTileSz();
//        else return curColPosX/gPanel.getActualTileSz();
//    }
//
//    private int currentRow(int y) {
//        if (y % gPanel.getActualTileSz() == 0) return y /gPanel.getActualTileSz();
//        int posYBot = y + gPanel.getActualTileSz();
//        int curRowPosY = (posYBot/gPanel.getActualTileSz()) * gPanel.getActualTileSz();
//        int distanceTop = curRowPosY - y;
//        int distanceBot = posYBot - curRowPosY;
//        if(distanceTop > distanceBot) return (curRowPosY - gPanel.getActualTileSz())/gPanel.getActualTileSz();
//        else return curRowPosY/gPanel.getActualTileSz();
//    }

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
