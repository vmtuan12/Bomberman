package event;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandle implements KeyListener {
    private GamePanel gPanel;
    private boolean up, down, left, right;
    private boolean placeBomb = false;

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isPlaceBomb() {
        return placeBomb;
    }

    public void setPlaceBomb(boolean placeBomb) {
        this.placeBomb = placeBomb;
    }

    public KeyHandle(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(gPanel.getGameState() == gPanel.gameMenu) {
            if(key == KeyEvent.VK_W) {
                if(gPanel.getMenuButton().getCursorPosition() < 2) {
                    gPanel.getMenuButton().setCursorPosition(gPanel.getMenuButton().getCursorPosition() + 1);
                }
            }
            if(key == KeyEvent.VK_S) {
                if(gPanel.getMenuButton().getCursorPosition() > 0) {
                    gPanel.getMenuButton().setCursorPosition(gPanel.getMenuButton().getCursorPosition() - 1);
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gPanel.getMenuButton().getCursorPosition() == 0) {
                    System.exit(0);
                }
                else if(gPanel.getMenuButton().getCursorPosition() == 2) {
                    gPanel.setGameState(gPanel.gamePlayed);
                    gPanel.stopMusic();
                }
            }

        }

        if(gPanel.getGameState() == gPanel.gamePlayed) {
            if(key == KeyEvent.VK_W) {
                up = true;
            }
            if(key == KeyEvent.VK_S) {
                down = true;
            }
            if(key == KeyEvent.VK_A) {
                left = true;
            }
            if(key == KeyEvent.VK_D) {
                right = true;
            }
            if(key == KeyEvent.VK_J) {
                placeBomb = true;
            }
            if(key == KeyEvent.VK_P) {
                if(gPanel.getGameState() == gPanel.gamePlayed) gPanel.setGameState(gPanel.gamePaused);
                else if(gPanel.getGameState() == gPanel.gamePaused) gPanel.setGameState(gPanel.gamePlayed);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            up = false;
        }
        if(key == KeyEvent.VK_S) {
            down = false;
        }
        if(key == KeyEvent.VK_A) {
            left = false;
        }
        if(key == KeyEvent.VK_D) {
            right = false;
        }
        if(key == KeyEvent.VK_J) {
            placeBomb = false;
        }
    }
}