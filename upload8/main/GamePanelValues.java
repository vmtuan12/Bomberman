package main;

import UI.menu.MenuBackground;
import UI.menu.MenuButton;
import creator.obj.bomb.Bomb;
import creator.obj.bomb.Explosion;
import creator.gameChar.Player;
import creator.obj.item.Item;
import event.CollisionDectection;
import event.KeyHandle;
import creator.obj.ObjCreator;
import creator.map.TileMng;
import event.Sound;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class GamePanelValues extends JPanel {
    // game values
    protected final int tileSz = 16;
    protected final int scale = 3;
    protected final int actualTileSz = tileSz * scale;
    protected final int screenW = 786;
    protected final int screenH = 624;
    protected final int bigMapCol = 30;
    protected final int bigMapRow = 30;
    protected final int fps = 60;
    protected MenuBackground menuBackground;
    protected MenuButton menuButton;
    // system
    protected Thread gameThread;
    protected KeyHandle key;
    protected CollisionDectection collisionDectection;

    // entity
    protected Player player;

    // map
    public TileMng tileMng;

    // items
    protected Item[] items = new Item[10];
    protected ObjCreator objCreator;

    // bomb
    protected Bomb bomb;
    public List<Bomb> bombList = new ArrayList<>();
    protected Explosion explosion;
    public List<Explosion> explosionList = new ArrayList<>();

    // sound
    protected Sound soundPlayer = new Sound();
    protected Sound musicPlayer = new Sound();

    // game state
    protected int gameState;
    public final int gameMenu = 0;
    public final int gamePlayed = 1;
    public final int gamePaused = 2;

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
    public ObjCreator getObjCreator() {
        return objCreator;
    }
    public Item[] getItems() {
        return items;
    }
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public MenuButton getMenuButton() {
        return menuButton;
    }
    public MenuBackground getMenuBackground() {
        return menuBackground;
    }

    // object controller
    public void setObjects(int n, Item obj, int x, int y) {
        items[n] = obj;
        items[n].setX(x);
        items[n].setY(y);
    }
    public void deleteObj(int type) {
        items[type] = null;
    }
}
