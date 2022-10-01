package creator.obj.item;

import main.GamePanel;

public class Timelock extends Item {

    public Timelock(GamePanel gPanel) {
        super(0,"srcImg/item/timelock.png",gPanel);
    }

    @Override
    public void action() {
        gPanel.playSound(2);
    }
}
