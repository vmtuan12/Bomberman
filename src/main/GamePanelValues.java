package main;

import UI.hud.DeadDisplay;
import UI.hud.ItemDisplay;
import UI.hud.ScoreDisplay;
import UI.menu.MenuBackground;
import UI.menu.MenuButton;
import creator.gameChar.monster.Monster;
import creator.gameChar.monster.MonsterCreator;
import creator.gameChar.monster.monsterAI.PathFinder;
import creator.obj.item.ItemCreator;
import creator.obj.bomb.Bomb;
import creator.obj.bomb.Explosion;
import creator.gameChar.player.Player;
import creator.obj.item.Item;
import event.CollisionDectection;
import event.KeyHandle;
import creator.map.TileMng;
import event.Sound;
import fileWriter.ScoreSetter;

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

    public ScoreSetter scoreSetter;
    public int score = 0;
    public PathFinder pathFinder;

    // map
    public TileMng tileMng;

    // items
    protected List<Item> items = new ArrayList<>();
    protected ItemCreator itemCreator;

    //hud
    protected ItemDisplay itemHUDDisplay;
    protected ScoreDisplay scoreDisplay;
    protected DeadDisplay deadDisplay;

    // bomb
    protected Bomb bomb;
    public List<Bomb> bombList = new ArrayList<>();
    public List<Bomb> bossBombList = new ArrayList<>();
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
    public final int gameChangeMap = 7;
    public final int gameWin = 8;
    public final int mapChangingPhaseTime = 80;
    public int mapChangingPhaseTimeCount = mapChangingPhaseTime;

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

    public List<Item> getItems() {
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

}
