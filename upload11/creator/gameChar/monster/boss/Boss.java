package creator.gameChar.monster.boss;

import creator.gameChar.monster.Monster;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boss extends Monster {
    public Boss(GamePanel gPanel, int x, int y, int direction) {
        super(gPanel, x, y, 8, 8,
                4, 0, direction, 48, 48);
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz()*2 - 8, gPanel.getActualTileSz()*2 - 8);
        healthBarLength = gPanel.getActualTileSz()*2;
        getImage();
    }

    private void getImage() {
        super.getImage(imageGetter.getBoss(),4,gPanel.getActualTileSz()*2);
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
