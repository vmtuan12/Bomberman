package UI.menu;

import UI.Status;
import main.GamePanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuButton extends Status {

    private Font m5x7;

    private int red = 100;
    private int green = 50;
    private int blue = 0;
    private boolean increment = true;

    private final String title = "BOMBERMAN";
    private final String newGame = "NEW GAME";
    private final String loadGame = "LOAD GAME";
    private final String exit = "EXIT";
    private final String cursor = "->";
    private int positionXOfText;
    private int cursorPosition = 2;
    public MenuButton(GamePanel gPanel) {
        super(gPanel);
        loadFont();
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
        g2.drawString(title,calculatePosXCenter(g2,title),100);
    }

    private void renderUI(Graphics2D g2) {
        g2.setFont(m5x7);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        g2.setColor(Color.black);
        positionXOfText = calculatePosXCenter(g2,newGame);
        g2.drawString(newGame,positionXOfText,300);
        if(cursorPosition == 2) {
            g2.drawString(cursor,positionXOfText - positionXOfText/5,300);
        }
        positionXOfText = calculatePosXCenter(g2,loadGame);
        g2.drawString(loadGame,positionXOfText,350);
        if(cursorPosition == 1) {
            g2.drawString(cursor,positionXOfText - positionXOfText/5,350);
        }
        positionXOfText = calculatePosXCenter(g2,exit);
        g2.drawString(exit,positionXOfText,400);
        if(cursorPosition == 0) {
            g2.drawString(cursor,positionXOfText - positionXOfText/5,400);
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
