package main;

import UI.hud.ItemDisplay;
import UI.menu.MenuBackground;
import UI.menu.MenuButton;
import creator.gameChar.monster.Monster;
import creator.gameChar.monster.MonsterCreator;
import creator.obj.item.ItemCreator;
import creator.obj.bomb.Bomb;
import creator.obj.bomb.Explosion;
import creator.gameChar.player.Player;
import creator.obj.item.Item;
import event.CollisionDectection;
import event.KeyHandle;
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
    protected List<Monster> monsters = new ArrayList<>();
    protected MonsterCreator monsterCreator;
    protected int score = 0;

    // map
    public TileMng tileMng;

    // items
    protected Item[] items = new Item[10];
    protected ItemCreator itemCreator;

    //hud
    protected ItemDisplay itemHUDDisplay;

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
    public final int gameTutorial = 1;
    public final int gameSetPlayerName = 2;
    public final int gameChoosingChar = 3;
    public final int gameHighscore = 4;
    public final int gamePlayed = 5;
    public final int gamePaused = 6;

    // getter and setter
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
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
    public KeyHandle getKey() {
        return key;
    }
    public Bomb getBomb() {
        return bomb;
    }
    public ItemCreator getItemCreator() {
        return itemCreator;
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
    public List<Monster> getMonsters() {
        return monsters;
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
