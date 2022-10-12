package UI.hud;

import UI.Status;
import main.GamePanel;

import java.awt.*;

public class ScoreDisplay extends Status {
    private Font font;
    private String scoreText;
    public ScoreDisplay(GamePanel gPanel) {
        super(gPanel);
        this.font = gPanel.getMenuButton().getM5x7();
    }

    public void renderScore(Graphics2D g2, int score) {
        scoreText = "SCORE " + score;
        g2.setFont(font);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        if(score < 180) g2.setColor(Color.white);
        else if(score < 480) g2.setColor(new Color(51,153,255));
        else if(score < 780) g2.setColor(new Color(255,0,255));
        else if(score < 1200) g2.setColor(Color.yellow);
        else g2.setColor(Color.red);
        g2.drawString(scoreText,calculatePosX(g2,scoreText),60);
    }

    private int calculatePosX(Graphics2D g2, String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gPanel.getScreenW() - textLength - 10;
    }
}
