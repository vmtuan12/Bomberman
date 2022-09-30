package UI;

import main.GamePanel;

import java.awt.*;

public class ItemTimelock extends Status {

    public ItemTimelock(GamePanel gPanel) {
        super(gPanel,16,16);
        loadImg("srcImg/item/timelock.png");
        updatePosition();
    }

    private void updatePosition() {
        setX(gPanel.getPlayer().getX());
        setY(gPanel.getPlayer().getY() - 16);
    }

    @Override
    public void renderImg(Graphics2D g2) {
        updatePosition();
        super.renderImg(g2);
    }
}
