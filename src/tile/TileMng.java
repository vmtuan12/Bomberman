package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileMng {

    GamePanel gPanel;
    Tile[] tiles;
    int[][] tileMap;

    public TileMng(GamePanel gp) {
        this.gPanel = gp;
        tiles = new Tile[30];
        tileMap = new int[gp.getBigMapRow()][gp.getBigMapCol()];
        getImage();
        createMap("tile/map2.txt");
    }

    public void getImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].img = ImageIO.read(new File("srcImg/block/floor_2.png"));
            tiles[1] = new Tile();
            tiles[1].img = ImageIO.read(new File("srcImg/block/crate3.png"));
            tiles[1].impassable = true;
            tiles[2] = new Tile();
            tiles[2].img = ImageIO.read(new File("srcImg/block/stone2.png"));
            tiles[2].impassable = true;
            tiles[3] = new Tile();
            tiles[3].img = ImageIO.read(new File("srcImg/block/wall_side_mid_left.png"));
            tiles[3].impassable = true;
            tiles[4] = new Tile();
            tiles[4].img = ImageIO.read(new File("srcImg/block/wall_side_mid_right.png"));
            tiles[4].impassable = true;
            tiles[5] = new Tile();
            tiles[5].img = ImageIO.read(new File("srcImg/block/wall.png"));
            tiles[5].impassable = true;
            tiles[6] = new Tile();
            tiles[6].img = ImageIO.read(new File("srcImg/block/corner_right.png"));
            tiles[6].impassable = true;
            tiles[7] = new Tile();
            tiles[7].img = ImageIO.read(new File("srcImg/block/corner_left.png"));
            tiles[7].impassable = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMap(String file) {
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
                if(col == gPanel.getBigMapCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkPosition(int x, int y) {
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().x - gPanel.getPlayer().getScreenX() &&
               x - gPanel.getActualTileSz() < gPanel.getPlayer().x + gPanel.getPlayer().getScreenX() &&
               y + gPanel.getActualTileSz() > gPanel.getPlayer().y - gPanel.getPlayer().getScreenY() &&
               y - gPanel.getActualTileSz() < gPanel.getPlayer().y + gPanel.getPlayer().getScreenY();
    }

    public void renderImg(Graphics2D g2) {
        int row = 0, col = 0;

        while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
            int type = tileMap[row][col];
            int x = col * gPanel.getActualTileSz();
            int y = row * gPanel.getActualTileSz();
            int screenX = x - gPanel.getPlayer().x + gPanel.getPlayer().getScreenX();
            int screenY = y - gPanel.getPlayer().y + gPanel.getPlayer().getScreenY();
            if(checkPosition(x, y)) {
                if(type != -1) g2.drawImage(tiles[type].img,screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
            }
            col++;

            if(col == gPanel.getBigMapCol()) {
                col = 0;
                row++;
            }
        }
    }
}
