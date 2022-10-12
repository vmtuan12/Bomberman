package creator.gameChar.monster.normal;

import creator.gameChar.monster.Monster;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cyclop extends Monster {
    public Cyclop(GamePanel gPanel, int x, int y, int direction) {
        super(gPanel, x, y, 8, 16,
                4, 1, direction, 2, 2);
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz() - 16, gPanel.getActualTileSz() - 20);
        healthBarLength = gPanel.getActualTileSz();
        getImage();
    }

    private void getImage() {
        super.getImage(imageGetter.getCyclop(),4,gPanel.getActualTileSz());
    }

    @Override
    public void update() {
        super.update();
        if(life <= maxLife/2 && !locked) {
            speed = 2;
            findPath();
        }
    }

    private void findPath() {
//        int startRow = this.y;
//        int startCol = this.x;
//        int finishRow = gPanel.getPlayer().getY();
//        int finishCol = gPanel.getPlayer().getX();
        int startRow = this.y/gPanel.getActualTileSz();
        int startCol = this.x/gPanel.getActualTileSz();
        int finishRow = gPanel.getPlayer().getY()/gPanel.getActualTileSz();
        int finishCol = gPanel.getPlayer().getX()/gPanel.getActualTileSz();
        System.out.println(startRow + " " + startCol + " "
                + finishRow + " " + finishCol + "dasdads");
        if(startRow < finishRow) {
            direction = 0; // down
        } else if(startRow > finishRow) {
            direction = 1; // up
        } else {
            if(startCol < finishCol) {
                direction = 3; // right
            } else {
                direction = 2; // left
            }
        }

    }

    @Override
    public void renderImg(Graphics2D g2) {
        super.renderImg(g2);
    }
}
