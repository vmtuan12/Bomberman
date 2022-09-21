package gameChar;

import main.GamePanel;
import keyHandle.KeyHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    private final KeyHandle key;
    private final GamePanel gPanel;

    private final int screenX;
    private final int screenY;

    public Player(KeyHandle key, GamePanel panel) {
        this.key = key;
        this.gPanel = panel;
        screenX = gPanel.getScreenW() / 2 - gPanel.getActualTileSz() / 2;
        screenY = gPanel.getScreenH() / 2 - gPanel.getActualTileSz() / 2;
        hitbox = new Rectangle(8, 16, gPanel.getActualTileSz() - 16, gPanel.getActualTileSz() - 20);
        up = new BufferedImage[7];
        down = new BufferedImage[7];
        left = new BufferedImage[7];
        right = new BufferedImage[7];
        setValue();
        getImage();
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setValue() {
        this.x = 44;
        this.y = 32;
        this.speed = 4;
        this.direction = 0;
    }

    private void getImage() {
        try {
            int[] posX = {0, 16, 32, 48};
            int posY = 0;
            for (int i = 0; i < 7; i++) {
                down[i] = ImageIO.read(new File("srcImg/char/char.png"));
                down[i] = down[i].getSubimage(posX[0], posY, gPanel.getTileSz(), gPanel.getTileSz());
                up[i] = ImageIO.read(new File("srcImg/char/char.png"));
                up[i] = up[i].getSubimage(posX[1], posY, gPanel.getTileSz(), gPanel.getTileSz());
                left[i] = ImageIO.read(new File("srcImg/char/char.png"));
                left[i] = left[i].getSubimage(posX[2], posY, gPanel.getTileSz(), gPanel.getTileSz());
                right[i] = ImageIO.read(new File("srcImg/char/char.png"));
                right[i] = right[i].getSubimage(posX[3], posY, gPanel.getTileSz(), gPanel.getTileSz());

                posY += gPanel.getTileSz();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (key.isUp() || key.isDown() || key.isRight() || key.isLeft()) {

            if (key.isUp()) {
                direction = 1;
            } else if (key.isDown()) {
                direction = 0;
            } else if (key.isLeft()) {
                direction = 2;
            } else if (key.isRight()) {
                direction = 3;
            }
            System.out.println(screenX + " " + screenY + " " + x + " " + y);

            collided = false;
            gPanel.getCollisionDectection().check(this);
            if (!collided) {
                if (direction == 0) y += speed;
                else if (direction == 1) y -= speed;
                else if (direction == 2) x -= speed;
                else if (direction == 3) x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 0) spriteNum = 1;
                else if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 4;
                else if (spriteNum == 4) spriteNum = 5;
                else if (spriteNum == 5) spriteNum = 6;
                else if (spriteNum == 6) spriteNum = 0;
                spriteCounter = 0;
            }
        }
    }

    public void renderImg(Graphics2D g2) {
//        Image img = Toolkit.getDefaultToolkit().getImage("Special2.png");
//        g2.drawImage(img, this.x, this.y, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);

        BufferedImage img;
        if (direction == 0) {
            if (key.isDown()) img = down[spriteNum];
            else img = down[0];
        } else if (direction == 1) {
            if (key.isUp()) img = up[spriteNum];
            else img = up[0];
        } else if (direction == 2) {
            if (key.isLeft()) img = left[spriteNum];
            else img = left[0];
        } else {
            if (key.isRight()) img = right[spriteNum];
            else img = right[0];
        }
        g2.drawImage(img, screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
    }
}
