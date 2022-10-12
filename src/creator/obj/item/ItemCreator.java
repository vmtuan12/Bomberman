package creator.obj.item;

import main.GamePanel;

public class ItemCreator {

    GamePanel gPanel;

    public ItemCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void createObj() {
        if(gPanel.tileMng.getMapCode() == 0) {
            gPanel.getItems().add(new Boots(gPanel,4*gPanel.getActualTileSz(), gPanel.getActualTileSz()));

        } else if(gPanel.tileMng.getMapCode() == 1) {
            gPanel.getItems().add(new Timelock(gPanel,2*gPanel.getActualTileSz(), gPanel.getActualTileSz()));
        } else {
            gPanel.getItems().add(new Boots(gPanel,gPanel.getActualTileSz(), 2*gPanel.getActualTileSz()));
        }

    }
}
