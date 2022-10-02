package creator.obj.item;

import main.GamePanel;

public class BombAdd extends Item {

    public BombAdd(GamePanel gPanel) {
        super(3,gPanel);
        loadImg(imageGetter.getItems()[3]);
    }

    @Override
    public void action() {
        gPanel.playSound(5);
        gPanel.getPlayer().setCurrentBombQuantity(gPanel.getPlayer().getCurrentBombQuantity() + 1);
    }
}
