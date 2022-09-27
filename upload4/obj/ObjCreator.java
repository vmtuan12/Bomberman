package obj;

import main.GamePanel;
import obj.item.Boots;
import obj.item.Life;
import obj.item.Timelock;

public class ObjCreator {

    GamePanel gPanel;

    public ObjCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

//    public void setObjPosition(int n, int posX, int posY) {
//        gPanel.objects[n].x = posX;
//        gPanel.objects[n].y = posY;
//    }

    public void createObj() {
        gPanel.setObjects(0,new Timelock(),gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(1,new Life(),2*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(2,new Boots(),3*gPanel.getActualTileSz(), gPanel.getActualTileSz());
    }
}
