package creator.obj;

import creator.obj.item.BombAdd;
import main.GamePanel;
import creator.obj.item.Boots;
import creator.obj.item.Life;
import creator.obj.item.Timelock;

public class ObjCreator {

    GamePanel gPanel;
    private int sz = 0;

    public ObjCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

//    public void setObjPosition(int n, int posX, int posY) {
//        gPanel.objects[n].x = posX;
//        gPanel.objects[n].y = posY;
//    }


    public int getSz() {
        return sz;
    }

    public void createObj() {
        gPanel.setObjects(sz++,new Timelock(),4*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(sz++,new Life(),2*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(sz++,new Boots(),3*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(sz++,new BombAdd(),5*gPanel.getActualTileSz(), gPanel.getActualTileSz());
    }
}
