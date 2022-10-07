package creator.gameChar.monster.normal;

import creator.gameChar.monster.Monster;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cyclop extends Monster {
    public Cyclop(GamePanel gPanel, int x, int y, int direction) {
        super(gPanel, x, y, 10, 10,
                4, 2, direction, 1, 1);
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz() - 10, gPanel.getActualTileSz() - 10);
        healthBarLength = gPanel.getActualTileSz();
        getImage();
    }

    private void getImage() {
        super.getImage(imageGetter.getCyclop(),4,gPanel.getActualTileSz());
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void renderImg(Graphics2D g2) {
        super.renderImg(g2);
    }
}
