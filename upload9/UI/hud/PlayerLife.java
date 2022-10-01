package UI.hud;

import main.GamePanel;

import java.awt.Graphics2D;

public class PlayerLife extends ItemDisplay {

    public PlayerLife(GamePanel gPanel) {
        super(gPanel,"srcImg/item/heart.png",
              gPanel.getActualTileSz()/2,gPanel.getActualTileSz()/2);
    }

    @Override
    public void renderImg(Graphics2D g2) {
        setX(gPanel.getActualTileSz()/2);
        int i = 0;
        for(i = 0; i < gPanel.getPlayer().getLife(); i++) {
            g2.drawImage(img,getX(),getY(),null);
            setX(getX() + gPanel.getActualTileSz());
        }
    }
}
