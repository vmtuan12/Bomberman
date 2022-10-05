package creator.obj.item;

import main.GamePanel;

public class Life extends Item {

    public Life(GamePanel gPanel) {
        super(1,gPanel);
        loadImg(imageGetter.getItems()[1]);
    }

    @Override
    public void action() {
        gPanel.playSound(3);
    }
}
