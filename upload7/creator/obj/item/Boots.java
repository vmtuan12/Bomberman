package creator.obj.item;

import main.GamePanel;

public class Boots extends Item {

    public Boots(GamePanel gPanel) {
        super(2,"srcImg/item/boots.png",gPanel);
    }

    @Override
    public void action() {
        gPanel.getPlayer().setSpeed(7);
        gPanel.playSound(4);
    }
}
