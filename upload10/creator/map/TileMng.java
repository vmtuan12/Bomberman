package creator.map;

import main.GamePanel;
import asset.ImageGetter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileMng {

    private final GamePanel gPanel;
    private final Tile[] tiles;
    private int[][] tileMap;

    public TileMng(GamePanel gp) {
        this.gPanel = gp;
        tiles = new Tile[30];
        tileMap = new int[gp.getBigMapRow()][gp.getBigMapCol()];
        getImage();
        createMap("tile/map1.txt");
    }

    private void getImage() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            BufferedImage scaledImg;
            Graphics2D g2;

            for (int i = 0; i < imageGetter.getBlock().length; i++) {
                tiles[i] = new Tile();
                tiles[i].img = ImageIO.read(new File(imageGetter.getBlock()[i]));

                scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz(),tiles[i].img.getType());
                g2 = scaledImg.createGraphics();
                g2.drawImage(tiles[i].img,0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
                tiles[i].img = scaledImg;
                g2.dispose();

                if (i != 0 && i != 12) tiles[i].setImpassable(true);
                if (i == 1) tiles[i].setBreakable(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMap(String file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            int row = 0, col = 0;
            while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
                String line = bufferedReader.readLine();
                while (col < gPanel.getBigMapCol()) {
                    String[] num = line.split(" ");
                    int type = Integer.parseInt(num[col]);
                    tileMap[row][col] = type;
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

    // render image just in the screen, not render outside the screen
    private boolean checkPosition(int x, int y) {
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    public void renderImg(Graphics2D g2) {
        int row = 0, col = 0;

        while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
            int type = tileMap[row][col];
            int x = col * gPanel.getActualTileSz();
            int y = row * gPanel.getActualTileSz();
            int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
            int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
            if (checkPosition(x, y)) {
                if (type != -1 && tiles[type].img != null)
                    g2.drawImage(tiles[type].img, screenX, screenY, null);
            }
            col++;

            if (col == gPanel.getBigMapCol()) {
                col = 0;
                row++;
            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int[][] getTileMap() {
        return tileMap;
    }

    // update block type at specific position
    public void setBlockType(int row, int col, int type) {
        tileMap[row][col] = type;
    }
}
