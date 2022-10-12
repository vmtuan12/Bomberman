package creator.gameChar.monster;

import asset.MapGetter;
import creator.gameChar.monster.boss.Boss;
import creator.gameChar.monster.normal.Bamboo;
import creator.gameChar.monster.normal.Cyclop;
import main.GamePanel;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MonsterCreator {
    private GamePanel gPanel;
    private int index = 0;
    private final int normalMonsterSize;
    public MonsterCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
        normalMonsterSize = gPanel.getActualTileSz();
    }

    public void createMonster(int mapCode) {
        try {
            gPanel.getMonsters().clear();
            MapGetter mapGetter = new MapGetter();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mapGetter.getMap()[mapCode]));
            int row = 0, col = 0;
            while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
                String line = bufferedReader.readLine();
                while (col < gPanel.getBigMapCol()) {
                    String[] num = line.split(" ");
                    if(num[col].equals("c")){
                        gPanel.getMonsters().add(new Cyclop(gPanel,normalMonsterSize*col,normalMonsterSize*row,3));
                    }
                    else if(num[col].equals("b")) {
                        gPanel.getMonsters().add(new Bamboo(gPanel,normalMonsterSize*col,normalMonsterSize*row,3));
                    }
                    else if(num[col].equals("d")) {
                        gPanel.getMonsters().add(new Boss(gPanel,normalMonsterSize*col,normalMonsterSize*row,3));
                    }
                    col++;
                }
                if (col == gPanel.getBigMapCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMonster() {
        if(gPanel.tileMng.getMapCode() == 0) {
            gPanel.getMonsters().clear();
            gPanel.getMonsters().add(new Bamboo(gPanel,96,48,3));
            gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3));
            gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3));
            gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3));
        }
        else if(gPanel.tileMng.getMapCode() == 1) {
            gPanel.getMonsters().clear();
            gPanel.getMonsters().add(new Bamboo(gPanel,96,48,3));
//            gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3));
//            gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3));
//            gPanel.getMonsters().add(new Cyclop(gPanel,96,48,3));

        }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
