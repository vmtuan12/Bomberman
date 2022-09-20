package main;

import javax.swing.JPanel;

public class GamePanelValues extends JPanel {
    final int tileSz = 16;
    final int scale = 3;
    final int actualTileSz = tileSz * scale;
    final int maxCol = 20;
    final int maxRow = 13;
    final int bigMapCol = 50;
    final int bigMapRow = 50;

    final int screenW = actualTileSz * maxCol; // 960
    final int screenH = actualTileSz * maxRow; // 624
    final int bigMapW = actualTileSz * bigMapCol;
    final int bigMapH = actualTileSz * bigMapRow;

    final int fps = 60;
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
}
