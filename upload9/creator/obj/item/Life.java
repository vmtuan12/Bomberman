package creator.obj.item;

import main.GamePanel;

public class Life extends Item {

    public Life(GamePanel gPanel) {
        super(1,"srcImg/item/heart.png",gPanel);
    }

    @Override
    public void action() {
        gPanel.playSound(3);
    }
}
