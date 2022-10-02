package creator.gameChar.monster;

import creator.gameChar.Entity;
import main.GamePanel;

public class Monster extends Entity {
    public Monster(GamePanel gPanel, int x, int y, int hitboxDefaultX, int hitboxDefaultY,
                   int sprite, int speed, int direction, int life, int maxLife) {
        super(gPanel, x, y, hitboxDefaultX, hitboxDefaultY,
                sprite, speed, direction, life, maxLife);
    }


}
