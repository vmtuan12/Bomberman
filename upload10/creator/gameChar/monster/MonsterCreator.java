package creator.gameChar.monster;

import creator.gameChar.monster.normal.Bamboo;
import creator.gameChar.monster.normal.Cyclop;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MonsterCreator {
    private int mapCode = 0;
    private GamePanel gPanel;
    private int index = 0;
    private List<Monster> deadMonster = new ArrayList<>();
    public MonsterCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void createMonster() {
        gPanel.getMonsters().clear();
        gPanel.getMonsters().add(new Bamboo(gPanel,96,48,3,0));
        gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3,1));
        gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3,1));
        gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3,1));
    }

    public void update() {
        for (int i = 0; i < gPanel.getMonsters().size(); i++) {
            gPanel.getMonsters().get(i).update();
        }
    }

    public void renderImg(Graphics2D g2) {
        for(index = 0; index < gPanel.getMonsters().size(); index++) {
            gPanel.getMonsters().get(index).renderImg(g2);
            if(gPanel.getMonsters().get(index).isDead()) {
                gPanel.getMonsters().remove(index);
                index--;
            }
        }
        index = 0;
//        for(index = 0; index < deadMonster.size(); index++) {
//
//        }

    }

    public int getMapCode() {
        return mapCode;
    }

    public void setMapCode(int mapCode) {
        this.mapCode = mapCode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
