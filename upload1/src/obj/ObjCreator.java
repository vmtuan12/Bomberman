package obj;

import main.GamePanel;

public class ObjCreator {

    GamePanel gPanel;

    public ObjCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void setObjPosition(int n, int posX, int posY) {
        gPanel.objects[n].x = posX;
        gPanel.objects[n].y = posY;
    }

    public void createObj() {

    }
}
