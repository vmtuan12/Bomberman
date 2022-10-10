package creator.obj.item;

import main.GamePanel;

public class Life extends Item {

    public Life(GamePanel gPanel, int x, int y) {
        super(1,gPanel,x,y);
        loadImg(imageGetter.getItems()[1]);
    }

    @Override
    public void action() {
        gPanel.playSound(3);
    }
}
