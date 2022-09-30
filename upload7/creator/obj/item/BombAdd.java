package creator.obj.item;

import main.GamePanel;

public class BombAdd extends Item {

    public BombAdd(GamePanel gPanel) {
        super(3,"srcImg/item/bomb.png",gPanel);
    }

    @Override
    public void action() {
        gPanel.playSound(5);
        gPanel.getBomb().setBombQuantity(gPanel.getBomb().getBombQuantity() + 1);
    }
}
