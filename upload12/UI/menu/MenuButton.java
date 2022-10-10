package UI.menu;

import UI.Status;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuButton extends Status {

    private Font m5x7;
    private int red = 100;
    private int green = 50;
    private int blue = 0;
    private boolean increment = true;
    private int positionXOfText;
    private int positionXOfCharDes;
    private int cursorPosition = 3;
    private ButtonName button;

    Scanner scanner;

    private final int charDesSize = gPanel.getActualTileSz()*2;

    public MenuButton(GamePanel gPanel) {
        super(gPanel);
        button = new ButtonName();
        imgArr = new BufferedImage[3];
        button.playerName = new StringBuilder();
        getScore();
        loadFont();
        getImg();
    }

    private void getScore() {
        try {
            scanner = new Scanner(new File("score/highscore.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (scanner.hasNext()) {
            button.scoreList[i] = scanner.nextLine();
            i++;
        }
    }

    private void getImg() {
        loadImg(imageGetter.getMenuTutorial());
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
        if(gPanel.getGameState() != gPanel.gameChangeMap) renderTitle(g2);
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
        // menu
        if(gPanel.getGameState() == gPanel.gameMenu) {
            gameMenu(g2);
        }
        else if(gPanel.getGameState() == gPanel.gameSetPlayerName) {
            gameSetName(g2);
        }
        // choosing character
        else if(gPanel.getGameState() == gPanel.gameChoosingChar) {
            choosingChar(g2);
        }
        // tutorial
        else if(gPanel.getGameState() == gPanel.gameTutorial) {
            gameTutorial(g2);
        }
        else if(gPanel.getGameState() == gPanel.gameHighscore) {
            gameHighscore(g2);
        }
        else if(gPanel.getGameState() == gPanel.gameChangeMap) {
            renderMapChangingPhase(g2);
        }
    }

    private void gameMenu(Graphics2D g2) {
        positionXOfText = calculatePosXCenter(g2,button.newGame);
        g2.setColor(Color.black);
        if(cursorPosition == 4) {
            g2.setColor(Color.cyan);
            g2.drawString(button.cursor,positionXOfText - positionXOfText/5,250);
        }
        g2.drawString(button.newGame,positionXOfText,250);

        positionXOfText = calculatePosXCenter(g2,button.loadGame);
        g2.setColor(Color.black);
        if(cursorPosition == 3) {
            g2.setColor(Color.cyan);
            g2.drawString(button.cursor,positionXOfText - positionXOfText/5,300);
        }
        g2.drawString(button.loadGame,positionXOfText,300);

        positionXOfText = calculatePosXCenter(g2,button.tutorial);
        g2.setColor(Color.black);
        if(cursorPosition == 2) {
            g2.setColor(Color.cyan);
            g2.drawString(button.cursor,positionXOfText - positionXOfText/5,350);
        }
        g2.drawString(button.tutorial,positionXOfText,350);

        positionXOfText = calculatePosXCenter(g2,button.highscore);
        g2.setColor(Color.black);
        if(cursorPosition == 1) {
            g2.setColor(Color.cyan);
            g2.drawString(button.cursor,positionXOfText - positionXOfText/5,400);
        }
        g2.drawString(button.highscore,positionXOfText,400);

        positionXOfText = calculatePosXCenter(g2,button.exit);
        g2.setColor(Color.black);
        if(cursorPosition == 0) {
            g2.setColor(Color.cyan);
            g2.drawString(button.cursor,positionXOfText - positionXOfText/5,450);
        }
        g2.drawString(button.exit,positionXOfText,450);

        positionXOfText = calculatePosXCenter(g2,button.pressEnter);
        g2.setColor(Color.black);
        g2.drawString(button.pressEnter,positionXOfText,550);
    }

    private void gameSetName(Graphics2D g2) {
        positionXOfText = calculatePosXCenter(g2,button.enterName);
        g2.drawString(button.enterName,positionXOfText,200);
        positionXOfText = calculatePosXCenter(g2,button.playerName.toString());
        g2.drawString(button.playerName.toString(),positionXOfText,350);
        positionXOfText = calculatePosXCenter(g2,button.pressEnter);
        g2.drawString(button.pressEnter,positionXOfText,550);
    }

    private void choosingChar(Graphics2D g2) {

        positionXOfText = calculatePosXCenter(g2,button.pressEsc);
        g2.drawString(button.pressEsc,positionXOfText,550);
        g2.setColor(Color.orange);
        positionXOfText = calculatePosXCenter(g2,button.chooseChar);
        g2.drawString(button.chooseChar,positionXOfText,200);
        if(imgArr[0] == null) System.out.println("vai lon choose char");

        positionXOfText = (positionXOfCharDes + positionXOfCharDes + charDesSize)/2 - 2 * (gPanel.getScreenW()/3) - 10;
//        System.out.println(positionXOfText + "?");
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        if(cursorPosition == 0) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.setColor(Color.black);
            g2.drawString(button.upCursor,positionXOfText,400);
        }
        positionXOfCharDes =  (gPanel.getScreenW()/3 - imgArr[0].getWidth())/2;
        g2.drawImage(imgArr[0],positionXOfCharDes,250,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        positionXOfText += gPanel.getScreenW()/3;
        if(cursorPosition == 1) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.setColor(Color.pink);
            g2.drawString(button.upCursor,positionXOfText,400);
        }
        positionXOfCharDes += gPanel.getScreenW()/3;
        g2.drawImage(imgArr[1],positionXOfCharDes,250,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        positionXOfText += gPanel.getScreenW()/3;
        if(cursorPosition == 2) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g2.setColor(Color.green);
            g2.drawString(button.upCursor,positionXOfText,400);
        }
        positionXOfCharDes += gPanel.getScreenW()/3;
//        System.out.println(positionXOfCharDes);
        g2.drawImage(imgArr[2],positionXOfCharDes,250,null);

    }

    private void gameTutorial(Graphics2D g2) {
        g2.drawImage(this.img,153,250,null);
        positionXOfText = calculatePosXCenter(g2,button.tutorial);
        g2.drawString(button.tutorial,positionXOfText,150);
        positionXOfText = calculatePosXCenter(g2,button.pressEsc);
        g2.drawString(button.pressEsc,positionXOfText,550);
    }

    private void gameHighscore(Graphics2D g2) {
        for(int i = 0; i < 3; i++) {
            positionXOfText = calculatePosXCenter(g2,button.scoreList[i]);
            g2.drawString(button.scoreList[i],positionXOfText,50*i + 200);
        }
        positionXOfText = calculatePosXCenter(g2,button.pressEsc);
        g2.drawString(button.pressEsc,positionXOfText,550);
    }

    public void gamePaused(Graphics2D g2) {
        g2.setFont(m5x7);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        g2.setColor(Color.white);

        positionXOfText = calculatePosXCenter(g2,button.gamePaused);
        g2.drawString(button.gamePaused,positionXOfText,100);
        positionXOfText = calculatePosXCenter(g2,button.pressP);
        g2.drawString(button.pressP,positionXOfText,500);
    }

    public void renderMapChangingPhase(Graphics2D g2) {
        String stage = button.stage + (gPanel.tileMng.getMapCode() + 1);
        positionXOfText = calculatePosXCenter(g2,stage);
//        System.out.println(gPanel.mapChangingPhaseTimeCount + "???");
        if (gPanel.mapChangingPhaseTimeCount != 0) {
            g2.drawString(stage,positionXOfText,300);
            gPanel.mapChangingPhaseTimeCount--;
            System.out.println(gPanel.mapChangingPhaseTimeCount);
        }
        if(gPanel.mapChangingPhaseTimeCount == 0) gPanel.setGameState(gPanel.gamePlayed);
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

    public ButtonName getButton() {
        return button;
    }

    public Font getM5x7() {
        return m5x7;
    }
}
