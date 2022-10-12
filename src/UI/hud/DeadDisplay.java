package UI.hud;

import UI.Status;
import main.GamePanel;

import java.awt.*;

public class DeadDisplay extends Status {
    private Font font;
    private final int startY = -10;
    private int y = startY;
    private final int deadRenderTime = 400;
    private int deadRenderTimeCount = deadRenderTime;
    private final String dead = "NGU";
    public DeadDisplay(GamePanel gPanel) {
        super(gPanel);
        this.font = gPanel.getMenuButton().getM5x7();
    }

    public void renderDead(Graphics2D g2) {
        g2.setFont(font);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        g2.setColor(Color.white);
        if(y < 300) y++;
        deadRenderTimeCount--;
        if(deadRenderTimeCount != 0) g2.drawString(dead,calculatePosX(g2,dead),y);
        else {
            y = startY;
            deadRenderTimeCount = deadRenderTime;
            gPanel.resetGame();
        }
    }

    private int calculatePosX(Graphics2D g2, String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gPanel.getScreenW()/2 - textLength/2);
    }
}
