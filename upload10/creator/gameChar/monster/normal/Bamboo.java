package creator.gameChar.monster.normal;

import creator.gameChar.monster.Monster;
import main.GamePanel;

import java.awt.*;

public class Bamboo extends Monster {
    public Bamboo(GamePanel gPanel, int x, int y, int direction, int code) {
        super(gPanel, x, y, 8, 8,
                4, 1, direction, 2, 2, code);
        getImage();
    }

    private void getImage() {
        super.getImage(imageGetter.getBamboo(), 4);
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
