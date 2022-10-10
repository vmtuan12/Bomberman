package creator.gameChar.monster.normal;

import creator.gameChar.monster.Monster;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bamboo extends Monster {
    public Bamboo(GamePanel gPanel, int x, int y, int direction) {
        super(gPanel, x, y, 8, 16,
                4, 2, direction, 1, 1);
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz() - 16, gPanel.getActualTileSz() - 20);
        healthBarLength = gPanel.getActualTileSz();
        getImage();
    }

    private void getImage() {
        super.getImage(imageGetter.getBamboo(), 4,gPanel.getActualTileSz());
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
