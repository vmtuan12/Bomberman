package creator.obj.item;

import main.GamePanel;

public class Boots extends Item {

    public Boots(GamePanel gPanel, int x, int y) {
        super(2,gPanel,x,y);
        loadImg(imageGetter.getItems()[2]);
    }

    @Override
    public void action() {
        gPanel.getPlayer().setSpeed(7);
        gPanel.playSound(4);
    }
}
