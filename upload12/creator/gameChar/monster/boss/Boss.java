package creator.gameChar.monster.boss;

import creator.gameChar.monster.Monster;
import creator.obj.bomb.Bomb;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boss extends Monster {

    private boolean mapChanged = false;
    private final int bombSize = gPanel.getActualTileSz();
    private final int startCol = 11;
    private final int startRow = 19;
    private int bombX;
    private int bombY;
    private int blockType;
    private final int deploySurroundingBombTime = 720;
    private int deploySurroundingBombTimeCount = deploySurroundingBombTime;
    private final int deployChasingBombTime = 1000;
    private int deployChasingBombTimeCount = deployChasingBombTime;
    private final int deployDirectedBombTime = 500;
    private int deployDirectedBombTimeCount = deployDirectedBombTime;
    public Boss(GamePanel gPanel, int x, int y, int direction) {
        super(gPanel, x, y, 8, 8,
                4, 0, direction, 48, 48);
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz()*4 - 8, gPanel.getActualTileSz()*4 - 8);
        healthBarLength = gPanel.getActualTileSz()*4;
        getImage();
    }

    private boolean playerEntered() {
        return gPanel.getPlayer().getY() >= 816;
    }

    private void changeMap() {
        if(playerEntered() && !mapChanged) {
            gPanel.tileMng.setBlockType(16,14,2);
            gPanel.tileMng.setBlockType(16,15,2);
            mapChanged = true;
        }
    }

    private void getImage() {
        super.getImage(imageGetter.getBoss(),4,gPanel.getActualTileSz()*4);
    }

    @Override
    public void update() {
        super.update();
        changeMap();
        if(mapChanged) attack();
    }

    private void attack() {
        deploySurroundingBomb();
        deployChasingBomb();
        if(life <= maxLife/4) deployDirectedBomb();
    }

    private void deploySurroundingBomb() {
        deploySurroundingBombTimeCount--;
        if(deploySurroundingBombTimeCount == 0) {
            for(int i = 0; i < 8; i++) {
                bombX = bombSize * (startCol+i);
                bombY = bombSize * startRow;
                blockType = gPanel.tileMng.getTileMap()[startRow][startCol + i];
                if(!gPanel.tileMng.getTiles()[blockType].isImpassable()) {
                    gPanel.bossBombList.add(new Bomb(gPanel,bombX,bombY));
                }
                bombY = bombSize * (startRow + 7);
                blockType = gPanel.tileMng.getTileMap()[startRow][startCol + i];
                if(!gPanel.tileMng.getTiles()[blockType].isImpassable()) {
                    gPanel.bossBombList.add(new Bomb(gPanel,bombX,bombY));
                }
            }
            for(int i = 1; i < 7; i++) {
                bombX = bombSize * startCol;
                bombY = bombSize * (startRow+i);
                blockType = gPanel.tileMng.getTileMap()[startRow + i][startCol];
                if(!gPanel.tileMng.getTiles()[blockType].isImpassable()) {
                    gPanel.bossBombList.add(new Bomb(gPanel,bombX,bombY));
                }
                bombX = bombSize * (startCol + 7);
                blockType = gPanel.tileMng.getTileMap()[startRow + i][startCol];
                if(!gPanel.tileMng.getTiles()[blockType].isImpassable()) {
                    gPanel.bossBombList.add(new Bomb(gPanel,bombX,bombY));
                }
            }
            deploySurroundingBombTimeCount = deploySurroundingBombTime;
        }
    }

    private void deployChasingBomb() {
        deployChasingBombTimeCount--;
        System.out.println(deployChasingBombTimeCount + "chase");
        if(deployChasingBombTimeCount <= 100) {
            if(deployChasingBombTimeCount % 30 == 0) gPanel.bossBombList.add(new Bomb(gPanel));
            if(deployChasingBombTimeCount <= 0) {
                deployChasingBombTimeCount = deployChasingBombTime;
            }
        }
    }

    private void deployDirectedBomb() {
        deployDirectedBombTimeCount--;
        if(gPanel.getPlayer().getX() <= this.x && gPanel.getPlayer().getY() <= this.y) {
            if(deployDirectedBombTimeCount <= 0) {
                for(int i = 2; i <= 5; i++) {
                    if(i%2 == 0) gPanel.bossBombList.add(new Bomb(gPanel,i*2*bombSize,22*bombSize));
                    else gPanel.bossBombList.add(new Bomb(gPanel,i*2*bombSize,23*bombSize));
                }
                gPanel.bossBombList.add(new Bomb(gPanel,9*bombSize,21*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,7*bombSize,19*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,6*bombSize,20*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,4*bombSize,18*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,14*bombSize,19*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,13*bombSize,17*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,10*bombSize,18*bombSize));
                deployDirectedBombTimeCount = deployDirectedBombTime;
            }
        }
        else if(gPanel.getPlayer().getX() <= this.x && gPanel.getPlayer().getY() > this.y) {
            if(deployDirectedBombTimeCount <= 0) {
                for(int i = 2; i <= 5; i++) {
                    if(i%2 == 0) gPanel.bossBombList.add(new Bomb(gPanel,i*2*bombSize,22*bombSize));
                    else gPanel.bossBombList.add(new Bomb(gPanel,i*2*bombSize,23*bombSize));
                }
                gPanel.bossBombList.add(new Bomb(gPanel,9*bombSize,24*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,7*bombSize,26*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,6*bombSize,25*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,4*bombSize,27*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,14*bombSize,26*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,13*bombSize,28*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,10*bombSize,27*bombSize));
                deployDirectedBombTimeCount = deployDirectedBombTime;
            }
        } else if(gPanel.getPlayer().getX() > this.x && gPanel.getPlayer().getY() < this.y) {
            if(deployDirectedBombTimeCount <= 0) {
                for(int i = 19; i <= 25; i += 2) {
                    if(i == 19 || i == 23) gPanel.bossBombList.add(new Bomb(gPanel,i*bombSize,22*bombSize));
                    else gPanel.bossBombList.add(new Bomb(gPanel,i*bombSize,23*bombSize));
                }
                gPanel.bossBombList.add(new Bomb(gPanel,20*bombSize,21*bombSize));//21
                gPanel.bossBombList.add(new Bomb(gPanel,22*bombSize,19*bombSize));//19
                gPanel.bossBombList.add(new Bomb(gPanel,25*bombSize,18*bombSize));//18
                gPanel.bossBombList.add(new Bomb(gPanel,23*bombSize,20*bombSize));//20
                gPanel.bossBombList.add(new Bomb(gPanel,15*bombSize,19*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,16*bombSize,17*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,19*bombSize,18*bombSize));
                deployDirectedBombTimeCount = deployDirectedBombTime;
            }
        } else if(gPanel.getPlayer().getX() > this.x && gPanel.getPlayer().getY() > this.y) {
            if(deployDirectedBombTimeCount <= 0) {
                for(int i = 19; i <= 25; i += 2) {
                    if(i == 19 || i == 23) gPanel.bossBombList.add(new Bomb(gPanel,i*bombSize,22*bombSize));
                    else gPanel.bossBombList.add(new Bomb(gPanel,i*bombSize,23*bombSize));
                }
                gPanel.bossBombList.add(new Bomb(gPanel,20*bombSize,24*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,22*bombSize,26*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,25*bombSize,27*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,23*bombSize,25*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,15*bombSize,26*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,16*bombSize,28*bombSize));
                gPanel.bossBombList.add(new Bomb(gPanel,19*bombSize,27*bombSize));
                deployDirectedBombTimeCount = deployDirectedBombTime;
            }
        }

    }

    @Override
    protected boolean checkPosition(int x, int y, GamePanel gPanel) {
        return x + gPanel.getActualTileSz()*4 > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz()*4 < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz()*4 > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz()*4 < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    @Override
    public void renderImg(Graphics2D g2) {
        super.renderImg(g2);
    }
}
