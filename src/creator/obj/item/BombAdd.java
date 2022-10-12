package creator.obj.item;

import main.GamePanel;

public class BombAdd extends Item {

    public BombAdd(GamePanel gPanel, int x, int y) {
        super(3,gPanel,x,y);
        loadImg(imageGetter.getItems()[3]);
    }

    @Override
    public void action() {
        gPanel.playSound(5);
        gPanel.getPlayer().setUnlimitedBomb(true);
    }
}
