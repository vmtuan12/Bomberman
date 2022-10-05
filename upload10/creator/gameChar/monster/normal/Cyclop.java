package creator.gameChar.monster.normal;

import creator.gameChar.monster.Monster;
import main.GamePanel;

import java.awt.*;

public class Cyclop extends Monster {
    public Cyclop(GamePanel gPanel, int x, int y, int direction, int code) {
        super(gPanel, x, y, 8, 8,
                4, 2, direction, 1, 1, code);
        getImage();
    }

    private void getImage() {
        super.getImage(imageGetter.getCyclop(),4);
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
