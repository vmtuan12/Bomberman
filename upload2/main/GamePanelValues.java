package main;

import bomb.Bomb;
import gameChar.Player;
import keyHandle.KeyHandle;
import obj.ObjCreator;
import bomb.Object;
import tile.TileMng;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class GamePanelValues extends JPanel {
    protected final int tileSz = 16;
    protected final int scale = 3;
    protected final int actualTileSz = tileSz * scale;
    protected final int maxCol = 16;
    protected final int maxRow = 13;
    protected final int bigMapCol = 30;
    protected final int bigMapRow = 30;

    protected final int screenW = actualTileSz * maxCol; // 960
    protected final int screenH = actualTileSz * maxRow; // 624
    protected final int bigMapW = actualTileSz * bigMapCol;
    protected final int bigMapH = actualTileSz * bigMapRow;

    protected final int fps = 60;

    protected Thread gameThread;
    protected final KeyHandle key = new KeyHandle();
    protected TileMng tileMng;
    protected Player player;
    protected CollisionDectection collisionDectection;
    public Object[] objects = new Object[10];
    protected ObjCreator objCreator;

    protected Bomb bomb;
    public List<Bomb> bombList = new ArrayList<>();

    public Player getPlayer() {
        return player;
    }

    public TileMng getTileMng() {
        return tileMng;
    }

    public CollisionDectection getCollisionDectection() {
        return collisionDectection;
    }
    public int getActualTileSz() {
        return actualTileSz;
    }

    public int getScreenW() {
        return screenW;
    }

    public int getScreenH() {
        return screenH;
    }

    public int getTileSz() {
        return tileSz;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getBigMapCol() {
        return bigMapCol;
    }

    public int getBigMapRow() {
        return bigMapRow;
    }

    public int getBigMapH() {
        return bigMapH;
    }

    public int getBigMapW() {
        return bigMapW;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(int n, Object obj) {
        objects[n] = obj;
    }
}
