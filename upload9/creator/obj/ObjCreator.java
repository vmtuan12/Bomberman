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

    public int getSz() {
        return sz;
    }
    public void setSz(int sz) {
        this.sz = sz;
    }

    public void createObj() {
        gPanel.setObjects(sz++,new Timelock(gPanel),4*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(sz++,new Life(gPanel),2*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(sz++,new Boots(gPanel),3*gPanel.getActualTileSz(), gPanel.getActualTileSz());
        gPanel.setObjects(sz++,new BombAdd(gPanel),5*gPanel.getActualTileSz(), gPanel.getActualTileSz());
    }
}
