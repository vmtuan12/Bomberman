package main;

import creator.obj.bomb.Bomb;
import creator.obj.bomb.Explosion;
import creator.gameChar.Player;
import keyHandle.KeyHandle;
import creator.obj.ObjCreator;
import creator.obj.Object;
import creator.map.TileMng;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class GamePanelValues extends JPanel {
    // game values
    protected final int tileSz = 16;
    protected final int scale = 3;
    protected final int actualTileSz = tileSz * scale;
    protected final int maxCol = 16;
    protected final int maxRow = 13;
    protected final int screenW = actualTileSz * maxCol; // 960
    protected final int screenH = actualTileSz * maxRow; // 624
    protected final int bigMapCol = 30;
    protected final int bigMapRow = 30;
    protected final int fps = 60;

    // system
    protected Thread gameThread;
    protected final KeyHandle key = new KeyHandle();
    protected CollisionDectection collisionDectection;

    // entity
    protected Player player;

    // map
    public TileMng tileMng;

    // items
    protected Object[] items = new Object[10];
    protected ObjCreator objCreator;

    // bomb
    protected Bomb bomb;
    public List<Bomb> bombList = new ArrayList<>();
    protected Explosion explosion;
    public List<Explosion> explosionList = new ArrayList<>();

    // sound
    protected Sound soundPlayer = new Sound();

    // getter and setter
    public Player getPlayer() {
        return player;
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
    public int getBigMapRow() {
        return bigMapRow;
    }
    public int getBigMapCol() {
        return bigMapCol;
    }
    public Bomb getBomb() {
        return bomb;
    }
    public Object[] getItems() {
        return items;
    }

    // object controller
    public void setObjects(int n, Object obj, int x, int y) {
        items[n] = obj;
        items[n].setX(x);
        items[n].setY(y);
    }
    public void deleteObj(int type) {
        items[type] = null;
    }
}
