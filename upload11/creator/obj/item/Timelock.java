package creator.obj.item;

import main.GamePanel;

public class Timelock extends Item {

    public Timelock(GamePanel gPanel, int x, int y) {
        super(0, gPanel,x,y);
        loadImg(imageGetter.getItems()[0]);
    }

    @Override
    public void action() {
        gPanel.playSound(2);
        for(int i = 0; i < gPanel.getMonsters().size(); i++) {
            gPanel.getMonsters().get(i).setLocked(true);
        }
    }
}
