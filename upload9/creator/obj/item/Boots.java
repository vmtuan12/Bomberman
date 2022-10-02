package creator.obj.item;

import main.GamePanel;

public class Boots extends Item {

    public Boots(GamePanel gPanel) {
        super(2,gPanel);
        loadImg(imageGetter.getItems()[2]);
    }

    @Override
    public void action() {
        gPanel.getPlayer().setSpeed(7);
        gPanel.playSound(4);
    }
}
