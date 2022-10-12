package event;

import creator.gameChar.player.Player;
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
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getCursorPosition() < 4) {
                    gPanel.getMenuButton().setCursorPosition(gPanel.getMenuButton().getCursorPosition() + 1);
                }
            }
            if(key == KeyEvent.VK_S) {
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getCursorPosition() > 0) {
                    gPanel.getMenuButton().setCursorPosition(gPanel.getMenuButton().getCursorPosition() - 1);
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getCursorPosition() == 0) {
                    System.exit(0);
                }
                else if(gPanel.getMenuButton().getCursorPosition() == 1) {
                    gPanel.setGameState(gPanel.gameHighscore);
                }
                else if(gPanel.getMenuButton().getCursorPosition() == 2) {
                    gPanel.setGameState(gPanel.gameTutorial);
                }
                else if(gPanel.getMenuButton().getCursorPosition() == 3) {
                    gPanel.stopMusic();
                    gPanel.setGameState(gPanel.gamePlayed);
                }
                else if(gPanel.getMenuButton().getCursorPosition() == 4) {
                    gPanel.setupNewgameWithoutMusic();
                    gPanel.setGameState(gPanel.gameSetPlayerName);
                }
            }
        }

        else if(gPanel.getGameState() == gPanel.gameSetPlayerName) {
            if((KeyEvent.VK_A <= key && key <= KeyEvent.VK_Z)
            || (KeyEvent.VK_0 <= key && key <= KeyEvent.VK_9) || key == KeyEvent.VK_SPACE) {
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getButton().playerName.length() < 17) {
                    gPanel.getMenuButton().getButton().playerName.append((char) key);
                }
            }
            else if(key == KeyEvent.VK_BACK_SPACE) {
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getButton().playerName.length() > 0) {
                    gPanel.getMenuButton().getButton().playerName.deleteCharAt(gPanel.getMenuButton().getButton().playerName.length()-1);
                }
            }
            else if(key == KeyEvent.VK_ENTER) {
                gPanel.playSound(6);
                gPanel.getMenuButton().setCursorPosition(0);
                gPanel.setGameState(gPanel.gameChoosingChar);
            }
            else if(key == KeyEvent.VK_ESCAPE) {
                gPanel.playSound(6);
                gPanel.getMenuButton().setCursorPosition(3);
                gPanel.setGameState(gPanel.gameMenu);
            }
        }

        else if(gPanel.getGameState() == gPanel.gameChoosingChar) {
            if(key == KeyEvent.VK_A) {
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getCursorPosition() > 0) {
                    gPanel.getMenuButton().setCursorPosition(gPanel.getMenuButton().getCursorPosition() - 1);
                }
            }
            else if(key == KeyEvent.VK_D) {
                gPanel.playSound(6);
                if(gPanel.getMenuButton().getCursorPosition() < 2) {
                    gPanel.getMenuButton().setCursorPosition(gPanel.getMenuButton().getCursorPosition() + 1);
                }
            }
            else if(key == KeyEvent.VK_ESCAPE) {
                gPanel.playSound(6);
                gPanel.getMenuButton().setCursorPosition(3);
                gPanel.setGameState(gPanel.gameMenu);
            }
            else if(key == KeyEvent.VK_ENTER) {
                gPanel.playSound(6);
                gPanel.setPlayer(new Player(this,gPanel,gPanel.getMenuButton().getCursorPosition()));
                gPanel.stopMusic();
                gPanel.setGameState(gPanel.gameChangeMap);
            }
        }

        else if(gPanel.getGameState() == gPanel.gameTutorial) {
            if(key == KeyEvent.VK_ESCAPE) {
                gPanel.playSound(6);
                gPanel.setGameState(gPanel.gameMenu);
            }
        }

        else if(gPanel.getGameState() == gPanel.gameHighscore) {
            if(key == KeyEvent.VK_ESCAPE) {
                gPanel.playSound(6);
                gPanel.getMenuButton().setCursorPosition(3);
                gPanel.setGameState(gPanel.gameMenu);
            }
        }

        else if(gPanel.getGameState() == gPanel.gamePlayed) {
            switch (key) {
                case KeyEvent.VK_W -> up = true;
                case KeyEvent.VK_S -> down = true;
                case KeyEvent.VK_A -> left = true;
                case KeyEvent.VK_D -> right = true;
            }
            if(key == KeyEvent.VK_J) {
                placeBomb = true;
            }
            if(key == KeyEvent.VK_P) {
                gPanel.setGameState(gPanel.gamePaused);
            }
            if(key == KeyEvent.VK_ESCAPE) {
                gPanel.playMusic(0);
                gPanel.setGameState(gPanel.gameMenu);
            }
        }

        else if(gPanel.getGameState() == gPanel.gamePaused) {
            if(key == KeyEvent.VK_P && gPanel.getPlayer().getLife() > 0) {
                gPanel.setGameState(gPanel.gamePlayed);
            }
            if(key == KeyEvent.VK_ESCAPE && gPanel.getPlayer().getLife() > 0) {
                gPanel.playMusic(0);
                gPanel.setGameState(gPanel.gameMenu);
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
