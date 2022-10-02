package creator.obj.bomb;

import main.GamePanel;

import java.awt.*;

public class Explosion extends BombCreator {

    private final Rectangle[] deadCross = new Rectangle[2];
    private boolean down, up, left, right;
    private int explosionTime = 60;

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
        deadCross[0] = new Rectangle(-48,0,144,48);
        deadCross[1] = new Rectangle(0,-48,48,144);
        action();
        loadImg(imageGetter.getExpl());
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
        this.deadCross[0].x += this.x;
        this.deadCross[0].y += this.y;
        this.deadCross[1].x += this.x;
        this.deadCross[1].y += this.y;
    }

    public void resetHitBoxPosition() {
        this.deadCross[0].x = -48;
        this.deadCross[0].y = 0;
        this.deadCross[1].x = 0;
        this.deadCross[1].y = -48;
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
