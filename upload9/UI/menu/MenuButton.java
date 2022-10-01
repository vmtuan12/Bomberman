package UI.menu;

import UI.Status;
import asset.ImageGetter;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuButton extends Status {

    private Font m5x7;
    private int red = 100;
    private int green = 50;
    private int blue = 0;
    private boolean increment = true;
    private int positionXOfText;
    private int positionXOfCharDes;
    private int cursorPosition = 3;
    ButtonName button;
    private final int charDesSize = gPanel.getActualTileSz()*2;

    public MenuButton(GamePanel gPanel) {
        super(gPanel);
        button = new ButtonName();
        imgArr = new BufferedImage[3];
        loadFont();
        getImg();
    }

    private void getImg() {
        ImageGetter imageGetter = new ImageGetter();
        loadImg("srcImg/menu/tutorial.png");
        loadImgArr(imageGetter.getCharDes(),charDesSize,charDesSize);

    }

    private void loadFont() {
        try {
            m5x7 = Font.createFont(Font.TRUETYPE_FONT, new File("srcFont/m5x7.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void renderButton(Graphics2D g2) {
        renderTitle(g2);
        renderUI(g2);
    }

    private void renderTitle(Graphics2D g2) {
        g2.setFont(m5x7);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,120F));
        g2.setColor(new Color(red,green,blue));
        updateColor();
        g2.drawString(button.title,calculatePosXCenter(g2,button.title),100);
    }

    private void renderUI(Graphics2D g2) {
        g2.setFont(m5x7);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        g2.setColor(Color.black);

        if(gPanel.getGameState() == gPanel.gameMenu) {
            positionXOfText = calculatePosXCenter(g2,button.newGame);
            g2.drawString(button.newGame,positionXOfText,300);
            if(cursorPosition == 3) {
                g2.drawString(button.cursor,positionXOfText - positionXOfText/5,300);
            }

            positionXOfText = calculatePosXCenter(g2,button.loadGame);
            g2.drawString(button.loadGame,positionXOfText,350);
            if(cursorPosition == 2) {
                g2.drawString(button.cursor,positionXOfText - positionXOfText/5,350);
            }

            positionXOfText = calculatePosXCenter(g2,button.tutorial);
            g2.drawString(button.tutorial,positionXOfText,400);
            if(cursorPosition == 1) {
                g2.drawString(button.cursor,positionXOfText - positionXOfText/5,400);
            }

            positionXOfText = calculatePosXCenter(g2,button.exit);
            g2.drawString(button.exit,positionXOfText,450);
            if(cursorPosition == 0) {
                g2.drawString(button.cursor,positionXOfText - positionXOfText/5,450);
            }
        }
        else if(gPanel.getGameState() == gPanel.gameChoosingChar) {
            choosingChar(g2);
        }
        else if(gPanel.getGameState() == gPanel.gameTutorial) {
            g2.drawImage(this.img,153,250,null);
            positionXOfText = calculatePosXCenter(g2,button.tutorial);
            g2.drawString(button.tutorial,positionXOfText,150);
            positionXOfText = calculatePosXCenter(g2,button.pressEsc);
            g2.drawString(button.pressEsc,positionXOfText,550);
        }
    }

    private void choosingChar(Graphics2D g2) {

        if(imgArr[0] == null) System.out.println("vai lon");
        positionXOfCharDes =  (gPanel.getScreenW()/3 - imgArr[0].getWidth())/2;
        g2.drawImage(imgArr[0],positionXOfCharDes,250,null);
        positionXOfCharDes += gPanel.getScreenW()/3;
        g2.drawImage(imgArr[1],positionXOfCharDes,250,null);
        positionXOfCharDes += gPanel.getScreenW()/3;
//        System.out.println(positionXOfCharDes);
        g2.drawImage(imgArr[2],positionXOfCharDes,250,null);

        positionXOfText = calculatePosXCenter(g2,button.pressEsc);
        g2.drawString(button.pressEsc,positionXOfText,550);
        g2.setColor(Color.orange);
        positionXOfText = calculatePosXCenter(g2,button.chooseChar);
        g2.drawString(button.chooseChar,positionXOfText,200);

        positionXOfText = (positionXOfCharDes + positionXOfCharDes + charDesSize)/2 - 2 * (gPanel.getScreenW()/3) - 10;
//        System.out.println(positionXOfText + "?");
        if(cursorPosition == 0) {
            g2.drawString(button.upCursor,positionXOfText,400);
        }
        positionXOfText += gPanel.getScreenW()/3;
        if(cursorPosition == 1) {
            g2.drawString(button.upCursor,positionXOfText,400);
        }
        positionXOfText += gPanel.getScreenW()/3;
        if(cursorPosition == 2) {
            g2.drawString(button.upCursor,positionXOfText,400);
        }

    }

    private void updateColor() {
        if(increment) {
            red++;
            green++;
            blue++;
            if(red == 255) increment = false;
        } else {
            red--;
            green--;
            blue--;
            if(blue == 0) increment = true;
        }
    }

    private int calculatePosXCenter(Graphics2D g2, String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gPanel.getScreenW()/2 - textLength/2);
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void setCursorPosition(int cursorPosition) {
        this.cursorPosition = cursorPosition;
    }
}
