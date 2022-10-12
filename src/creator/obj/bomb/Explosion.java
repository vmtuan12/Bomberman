package creator.obj.bomb;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends BombCreator {

    private final Rectangle[] deadCross = new Rectangle[2];
    private boolean down, up, left, right;

    private int explosionTime = 10;

    public List<Boolean> damageMonster = new ArrayList<>();

    public Explosion(GamePanel gPanel) {
        super(gPanel);
        loadImg(imageGetter.getExpl());
    }

    public Explosion(GamePanel gPanel, int x, int y, boolean down, boolean up, boolean left, boolean right) {
        super(gPanel);
        this.x = x;
        this.y = y;
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
        setZone();
        System.out.println(down + " " + up + " " + left + " " + right + " dulr");
        System.out.println(deadCross[0] + "ngangngng");
        System.out.println(deadCross[1] + "docococ");
        for(int i = 0; i < gPanel.getMonsters().size(); i++) {
            damageMonster.add(true);
        }
        System.out.println(damageMonster.size());
        action();
        loadImg(imageGetter.getExpl());
    }

    private void setZone() {
        System.out.println(this.x + " " + this.y + "aaaaaaaa");
        deadCross[0] = new Rectangle();
        deadCross[1] = new Rectangle();
        deadCross[0].y = this.y;
        deadCross[0].height = gPanel.getActualTileSz();
        if(left && right) {
            deadCross[0].x = this.x - 48;
            deadCross[0].width = 144;
        }
        if (left && !right) {
            deadCross[0].x = this.x - 48;
            deadCross[0].width = 96;
        }
        if (right && !left) {
            deadCross[0].x = this.x;
            deadCross[0].width = 96;
        }
        if(!left && !right) {
            deadCross[0].x = this.x;
            deadCross[0].width = 48;
        }
        deadCross[1].x = this.x;
        deadCross[1].width = gPanel.getActualTileSz();
        if(down && up) {
            deadCross[1].y = this.y - 48;
            deadCross[1].height = 144;
        } else if(up) {
            deadCross[1].y = this.y - 48;
            deadCross[1].height = 96;
        } else if(down) {
            deadCross[1].y = this.y;
            deadCross[1].height = 96;
        } else {
            deadCross[1].y = this.y;
            deadCross[1].height = 48;
        }
    }

    public void explosionControl() {
//        List<Integer> bombIndexesToBeRemoved = new ArrayList<>();
//        int pos = 0;
        for(int i = 0; i < gPanel.explosionList.size(); i++) {
            Explosion e = gPanel.explosionList.get(i);
            if(gPanel.getGameState() == gPanel.gamePlayed) e.explosionTime--;
            if(e.explosionTime == 0) {
                gPanel.explosionList.remove(e);
                i--;
            }
//            pos++;
        }
    }

    public void explode(Graphics2D g2) {
        renderImg(g2,this.x,this.y, 10);
//        updateHitBoxPosition();

        if(down)  {
            renderImg(g2,this.x,this.y + gPanel.getActualTileSz(), 10);
        }
        if(up)  {
            renderImg(g2,this.x,this.y - gPanel.getActualTileSz(), 10);
        }
        if(left) {
            renderImg(g2, this.x - gPanel.getActualTileSz(), this.y, 10);
        }
        if(right) {
            renderImg(g2, this.x + gPanel.getActualTileSz(), this.y, 10);
        }
    }

    public void renderExplosion(Graphics2D g2) {
        explosionControl();
        if(gPanel.explosionList.size() != 0) {
            for(Explosion e : gPanel.explosionList) {
                e.explode(g2);
            }
        }
    }

    public void updateHitBoxPosition() {

//        System.out.println("left:" + left + " right:" + right + " down:" + down + " up:" + up);
//        System.out.println(deadCross[0] + "ngang");
//        System.out.println(deadCross[1] + "doc");
        this.deadCross[0].x += this.x;
        this.deadCross[0].y += this.y;
        this.deadCross[1].x += this.x;
        this.deadCross[1].y += this.y;
    }

    public void resetHitBoxPosition() {
        this.deadCross[0].x -= this.x;
        this.deadCross[0].y -= this.y;
        this.deadCross[1].x -= this.x;
        this.deadCross[1].y -= this.y;
    }

    public Rectangle[] getDeadCross() {
        return deadCross;
    }

    @Override
    public void action() {
        gPanel.playSound(1);
    }


    //    public void checkDown(GamePanel gPanel) {
//        int obstacle1 = gPanel.getTileMng().getTileMap()[bombBotRow][bombLeftCol];
//        int obstacle2 = gPanel.getTileMng().getTileMap()[bombBotRow][bombRightCol];
//        if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
//
//        }
//    }
}
