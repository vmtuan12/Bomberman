package event;

import creator.gameChar.Entity;
import creator.gameChar.monster.Monster;
import creator.gameChar.monster.boss.Boss;
import creator.obj.bomb.Bomb;
import main.GamePanel;

import java.util.List;

public class CollisionDectection {

    private final GamePanel gPanel;

    public CollisionDectection(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void checkMapCollision(Entity entity) {
        int entityLeftPosX = entity.getX() + entity.getHitbox().x;
        int entityRightPosX = entity.getX() + entity.getHitbox().x + entity.getHitbox().width;
        int entityTopPosY = entity.getY() + entity.getHitbox().y;
        int entityBotPosY = entity.getY() + entity.getHitbox().y + entity.getHitbox().height;

        int entityLeftCol = entityLeftPosX / gPanel.getActualTileSz();
        int entityRightCol = entityRightPosX / gPanel.getActualTileSz();
        int entityTopRow = entityTopPosY / gPanel.getActualTileSz();
        int entityBotRow = entityBotPosY / gPanel.getActualTileSz();

        // create 2 int of 2 obstacles type in front, check whether it is passable or not
        switch (entity.getDirection()) {
            case 0 -> {
                entityBotRow = (entityBotPosY + entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityBotRow][entityLeftCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityBotRow][entityRightCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                    if(gPanel.tileMng.getTiles()[obstacle1].isImpassable() && !gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setX(entity.getX() + 1);
                    }
                    if(!gPanel.tileMng.getTiles()[obstacle1].isImpassable() && gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setX(entity.getX() - 1);
                    }
                }
            }
            case 1 -> {
                entityTopRow = (entityTopPosY - entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityTopRow][entityLeftCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityTopRow][entityRightCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                    if(gPanel.tileMng.getTiles()[obstacle1].isImpassable() && !gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setX(entity.getX() + 1);
                    }
                    if(!gPanel.tileMng.getTiles()[obstacle1].isImpassable() && gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setX(entity.getX() - 1);
                    }
                }
            }
            case 2 -> {
                entityLeftCol = (entityLeftPosX - entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityTopRow][entityLeftCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityBotRow][entityLeftCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                    if(gPanel.tileMng.getTiles()[obstacle1].isImpassable() && !gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setY(entity.getY() + 1);
                    }
                    if(!gPanel.tileMng.getTiles()[obstacle1].isImpassable() && gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setY(entity.getY() - 1);
                    }
                }
            }
            case 3 -> {
                entityRightCol = (entityRightPosX + entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityTopRow][entityRightCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityBotRow][entityRightCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                    if(gPanel.tileMng.getTiles()[obstacle1].isImpassable() && !gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setY(entity.getY() + 1);
                    }
                    if(!gPanel.tileMng.getTiles()[obstacle1].isImpassable() && gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                        entity.setY(entity.getY() - 1);
                    }
                }
            }
        }
    }

    public int checkCollisionObj(Entity entity, boolean player) {
        int index = -1;
        for(int i = 0; i < gPanel.getItems().size(); i++) {
            if(gPanel.getItems().get(i) != null) {
                entity.setHitboxX(entity.getX() + entity.getHitbox().x);
                entity.setHitboxY(entity.getY() + entity.getHitbox().y);
                gPanel.getItems().get(i).setHitboxX(gPanel.getItems().get(i).getX() + gPanel.getItems().get(i).getHitbox().x);
                gPanel.getItems().get(i).setHitboxY(gPanel.getItems().get(i).getY() + gPanel.getItems().get(i).getHitbox().y);

                switch (entity.getDirection()) {
                    case 0 -> { // down
                        entity.setHitboxY(entity.getHitbox().y + entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems().get(i).getHitbox())) {
                            if (gPanel.getItems().get(i).isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                    case 1 -> { // up
                        entity.setHitboxY(entity.getHitbox().y - entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems().get(i).getHitbox())) {
                            if (gPanel.getItems().get(i).isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                    case 2 -> { // left
                        entity.setHitboxX(entity.getHitbox().x - entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems().get(i).getHitbox())) {
                            if (gPanel.getItems().get(i).isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                    case 3 -> { // right
                        entity.setHitboxX(entity.getHitbox().x + entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems().get(i).getHitbox())) {
                            if (gPanel.getItems().get(i).isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                }
                entity.setHitboxX(entity.getHitboxDefaultX());
                entity.setHitboxY(entity.getHitboxDefaultY());
                gPanel.getItems().get(i).setHitboxX(gPanel.getItems().get(i).getHitboxDefaultX());
                gPanel.getItems().get(i).setHitboxY(gPanel.getItems().get(i).getHitboxDefaultY());
            }
        }
        return index;
    }

    public void checkCollisionBomb(Entity entity, List<Bomb> bombList) {
        for(int i = 0; i < bombList.size(); i++) {
            if(bombList.get(i) != null) {
                entity.setHitboxX(entity.getX() + entity.getHitbox().x);
                entity.setHitboxY(entity.getY() + entity.getHitbox().y);
                bombList.get(i).setHitboxX(bombList.get(i).getX() + bombList.get(i).getHitbox().x);
                bombList.get(i).setHitboxY(bombList.get(i).getY() + bombList.get(i).getHitbox().y);

                switch (entity.getDirection()) {
                    case 0 -> { // down
                        entity.setHitboxY(entity.getHitbox().y + entity.getSpeed());
                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
                            if(checkDown(entity,bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                    case 1 -> { // up
                        entity.setHitboxY(entity.getHitbox().y - entity.getSpeed());
//                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
//                            entity.setCollided(true);
//                        }
                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
                            if(checkUp(entity,bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                    case 2 -> { // left
                        entity.setHitboxX(entity.getHitbox().x - entity.getSpeed());
//                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
//                            entity.setCollided(true);
//                        }
                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
                            if(checkLeft(entity,bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                    case 3 -> { // right
                        entity.setHitboxX(entity.getHitbox().x + entity.getSpeed());
//                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
//                            entity.setCollided(true);
//                        }
                        if (entity.getHitbox().intersects(bombList.get(i).getHitbox())) {
                            if(checkRight(entity,bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                }
                entity.setHitboxX(entity.getHitboxDefaultX());
                entity.setHitboxY(entity.getHitboxDefaultY());
                bombList.get(i).setHitboxX(bombList.get(i).getHitboxDefaultX());
                bombList.get(i).setHitboxY(bombList.get(i).getHitboxDefaultY());
            }
        }
    }

    private boolean checkDown(Entity entity, Bomb b) {
        return b.getHitbox().y <= entity.getHitbox().y + entity.getHitbox().height
               && entity.getHitbox().y + entity.getHitbox().height <= b.getHitbox().y + entity.getSpeed();
    }

    private boolean checkUp(Entity entity, Bomb b) {
//        System.out.println(b.getHitbox().y + b.getHitbox().height - 2*entity.getSpeed() + "-" + entity.getHitbox().y);
//        System.out.println((b.getHitbox().y + b.getHitbox().height - entity.getSpeed() < entity.getHitbox().y)
//        + " " + (entity.getHitbox().y < b.getHitbox().y + b.getHitbox().height));
        return b.getHitbox().y + b.getHitbox().height - entity.getSpeed() <= entity.getHitbox().y
               && entity.getHitbox().y <= b.getHitbox().y + b.getHitbox().height;
    }

    private boolean checkRight(Entity entity, Bomb b) {
//        System.out.println(b.getHitbox().x + "-" + (entity.getHitbox().x + entity.getHitbox().width));
        return b.getHitbox().x <= entity.getHitbox().x + entity.getHitbox().width
               && entity.getHitbox().x + entity.getHitbox().width <= b.getHitbox().x + entity.getSpeed();
    }

    private boolean checkLeft(Entity entity, Bomb b) {
        return b.getHitbox().x + b.getHitbox().width - entity.getSpeed() <= entity.getHitbox().x
               && entity.getHitbox().x <= b.getHitbox().x + b.getHitbox().width;
    }

    public void checkCollisionBombForMonster(Monster monster) {
        for(int i = 0; i < gPanel.bombList.size(); i++) {
            monster.setHitboxX(monster.getX() + monster.getHitbox().x);
            monster.setHitboxY(monster.getY() + monster.getHitbox().y);
            gPanel.bombList.get(i).setHitboxX(gPanel.bombList.get(i).getX() + gPanel.bombList.get(i).getHitbox().x);
            gPanel.bombList.get(i).setHitboxY(gPanel.bombList.get(i).getY() + gPanel.bombList.get(i).getHitbox().y);

            if(monster.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
                monster.setCollided(true);
            }

            monster.setHitboxX(monster.getHitboxDefaultX());
            monster.setHitboxY(monster.getHitboxDefaultY());
            gPanel.bombList.get(i).setHitboxX(gPanel.bombList.get(i).getHitboxDefaultX());
            gPanel.bombList.get(i).setHitboxY(gPanel.bombList.get(i).getHitboxDefaultY());
        }
    }

    public void checkExplosionZoneForPlayer(Entity entity) {
        for(int i = 0; i < gPanel.explosionList.size(); i++) {
//            System.out.println(gPanel.explosionList.get(i).getX() + "_" +gPanel.explosionList.get(i).getY());
            entity.setHitboxX(entity.getX() + entity.getHitbox().x);
            entity.setHitboxY(entity.getY() + entity.getHitbox().y);
            gPanel.explosionList.get(i).updateHitBoxPosition();
            for(int j = 0; j < gPanel.explosionList.get(i).getDeadCross().length; j++) {
//                System.out.println(j + "-" +gPanel.explosionList.get(i).getDeadCross()[j]);
//                System.out.println(gPanel.explosionList.get(i).getDeadCross()[j] + "?????");
//                System.out.println(entity.getHitbox() + "dasda");
                if(entity.getHitbox().intersects(gPanel.explosionList.get(i).getDeadCross()[j])) {
//                    System.out.println("vai loansdoasdna");
                    if(!entity.isBlur()) {
                        gPanel.playSound(7);
//                        System.out.println("chet");
                        entity.setLife(entity.getLife()-1);
                        System.out.println(entity.getLife() + "vc");
//                        System.out.println(entity.getLife() + " life");
                        entity.setBlur(true);
                    }

                }
            }
            entity.setHitboxX(entity.getHitboxDefaultX());
            entity.setHitboxY(entity.getHitboxDefaultY());
            gPanel.explosionList.get(i).resetHitBoxPosition();
        }
    }

    public void checkExplosionZoneForMonster(List<Monster> monsters) {
        for(int i = 0; i < gPanel.explosionList.size(); i++) {
//            gPanel.explosionList.get(i).updateHitBoxPosition();
//            System.out.println(gPanel.explosionList.get(i).getX() + "_" +gPanel.explosionList.get(i).getY());
            for (int j = 0; j < monsters.size(); j++) {
                monsters.get(j).setHitboxX(monsters.get(j).getX() + monsters.get(j).getHitbox().x);
                monsters.get(j).setHitboxY(monsters.get(j).getY() + monsters.get(j).getHitbox().y);

                for(int k = 0; k < gPanel.explosionList.get(i).getDeadCross().length; k++) {
//                System.out.println(k + "-" +gPanel.explosionList.get(i).getDeadCross()[k]);
                    if(monsters.get(j).getHitbox().intersects(gPanel.explosionList.get(i).getDeadCross()[k])) {

                        if(gPanel.explosionList.get(i).damageMonster.get(j)) {
                            monsters.get(j).setGetDamaged(true);
                            monsters.get(j).setLife(monsters.get(j).getLife() - 1);
                            if(monsters.get(j) instanceof Boss) {
                                gPanel.playSound(8);
                            }
//                            System.out.println(monsters.get(j).getLife());
//                            System.out.println(monsters.get(j).getLife() + "life");
                            gPanel.explosionList.get(i).damageMonster.set(j,false);
                        }

                    }
                }
                monsters.get(j).setHitboxX(monsters.get(j).getHitboxDefaultX());
                monsters.get(j).setHitboxY(monsters.get(j).getHitboxDefaultY());
            }
            gPanel.explosionList.get(i).resetHitBoxPosition();
        }
    }

    public int checkCollisionMonster(Entity entity, List<Monster> monsters) {
        int index = -1;
        for(int i = 0; i < monsters.size(); i++) {
            if(monsters.get(i) != null && monsters.get(i).getLife() > 0) {
                entity.setHitboxX(entity.getX() + entity.getHitbox().x);
                entity.setHitboxY(entity.getY() + entity.getHitbox().y);
                monsters.get(i).setHitboxX(monsters.get(i).getX() + monsters.get(i).getHitbox().x);
                monsters.get(i).setHitboxY(monsters.get(i).getY() + monsters.get(i).getHitbox().y);

                switch (entity.getDirection()) {
                    case 0 -> { // down
                        entity.setHitboxY(entity.getHitbox().y + entity.getSpeed());
                        if (entity.getHitbox().intersects(monsters.get(i).getHitbox())) {
                            entity.setCollided(true);
                            gPanel.playSound(7);
                            index = i;
                        }
                    }
                    case 1 -> { // up
                        entity.setHitboxY(entity.getHitbox().y - entity.getSpeed());
                        if (entity.getHitbox().intersects(monsters.get(i).getHitbox())) {
                            entity.setCollided(true);
                            gPanel.playSound(7);
                            index = i;
                        }
                    }
                    case 2 -> { // left
                        entity.setHitboxX(entity.getHitbox().x - entity.getSpeed());
                        if (entity.getHitbox().intersects(monsters.get(i).getHitbox())) {
                            entity.setCollided(true);
                            gPanel.playSound(7);
                            index = i;
                        }
                    }
                    case 3 -> { // right
                        entity.setHitboxX(entity.getHitbox().x + entity.getSpeed());
                        if (entity.getHitbox().intersects(monsters.get(i).getHitbox())) {
                            entity.setCollided(true);
                            gPanel.playSound(7);
                            index = i;
                        }
                    }
                }
                entity.setHitboxX(entity.getHitboxDefaultX());
                entity.setHitboxY(entity.getHitboxDefaultY());
                monsters.get(i).setHitboxX(monsters.get(i).getHitboxDefaultX());
                monsters.get(i).setHitboxY(monsters.get(i).getHitboxDefaultY());
            }
        }
        return index;
    }

    public void checkMonsterCollidePlayer(Entity entity) {
        if(entity.getLife() > 0) {
            entity.setHitboxX(entity.getX() + entity.getHitbox().x);
            entity.setHitboxY(entity.getY() + entity.getHitbox().y);
            gPanel.getPlayer().setHitboxX(gPanel.getPlayer().getX() + gPanel.getPlayer().getHitbox().x);
            gPanel.getPlayer().setHitboxY(gPanel.getPlayer().getY() + gPanel.getPlayer().getHitbox().y);

            switch (entity.getDirection()) {
                case 0 -> { // down
                    entity.setHitboxY(entity.getHitbox().y + entity.getSpeed());
                    if (entity.getHitbox().intersects(gPanel.getPlayer().getHitbox())) {
                        entity.setCollided(true);
                        if(!gPanel.getPlayer().isBlur() && entity.getLife() > 0) {
                            gPanel.getPlayer().setBlur(true);
                            gPanel.getPlayer().setLife(gPanel.getPlayer().getLife() - 1);
                            gPanel.playSound(7);
                        }
                    }
                }
                case 1 -> { // up
                    entity.setHitboxY(entity.getHitbox().y - entity.getSpeed());
                    if (entity.getHitbox().intersects(gPanel.getPlayer().getHitbox())) {
                        entity.setCollided(true);
                        if(!gPanel.getPlayer().isBlur() && entity.getLife() > 0) {
                            gPanel.getPlayer().setBlur(true);
                            gPanel.getPlayer().setLife(gPanel.getPlayer().getLife() - 1);
                            gPanel.playSound(7);
                        }
                    }
                }
                case 2 -> { // left
                    entity.setHitboxX(entity.getHitbox().x - entity.getSpeed());
                    if (entity.getHitbox().intersects(gPanel.getPlayer().getHitbox())) {
                        entity.setCollided(true);
                        if(!gPanel.getPlayer().isBlur() && entity.getLife() > 0) {
                            gPanel.getPlayer().setBlur(true);
                            gPanel.getPlayer().setLife(gPanel.getPlayer().getLife() - 1);
                            gPanel.playSound(7);
                        }
                    }
                }
                case 3 -> { // right
                    entity.setHitboxX(entity.getHitbox().x + entity.getSpeed());
                    if (entity.getHitbox().intersects(gPanel.getPlayer().getHitbox())) {
                        entity.setCollided(true);
                        if(!gPanel.getPlayer().isBlur() && entity.getLife() > 0) {
                            gPanel.getPlayer().setBlur(true);
                            gPanel.getPlayer().setLife(gPanel.getPlayer().getLife() - 1);
                            gPanel.playSound(7);
                        }
                    }
                }
            }
            entity.setHitboxX(entity.getHitboxDefaultX());
            entity.setHitboxY(entity.getHitboxDefaultY());
            gPanel.getPlayer().setHitboxX(gPanel.getPlayer().getHitboxDefaultX());
            gPanel.getPlayer().setHitboxY(gPanel.getPlayer().getHitboxDefaultY());
        }
    }

    public void checkGameWin() {
        gPanel.getPlayer().setHitboxX(gPanel.getPlayer().getX() + gPanel.getPlayer().getHitbox().x);
        gPanel.getPlayer().setHitboxY(gPanel.getPlayer().getY() + gPanel.getPlayer().getHitbox().y);
//        System.out.println(gPanel.tileMng.getEscapeBlock() + "es");
//        System.out.println(gPanel.getPlayer().getHitbox() + "pl");
//        System.out.println(gPanel.getPlayer().getHitbox().intersects(gPanel.tileMng.getEscapeBlock()));
        if(gPanel.getMonsters().size() == 0
           && gPanel.getPlayer().getHitbox().intersects(gPanel.tileMng.getEscapeBlock())) {
            if(gPanel.tileMng.getMapCode() < 2) {
                gPanel.tileMng.setMapCode(gPanel.tileMng.getMapCode() + 1);
                System.out.println(gPanel.tileMng.getMapCode() + "code");
                gPanel.setupNextMap();
            }
            else if(gPanel.tileMng.getMapCode() == 2) {
                gPanel.setGameState(gPanel.gameWin);
                gPanel.scoreSetter.setScore(gPanel.getMenuButton().getButton().playerName.toString(), gPanel.score);
            }
        }
        gPanel.getPlayer().setHitboxX(gPanel.getPlayer().getHitboxDefaultX());
        gPanel.getPlayer().setHitboxY(gPanel.getPlayer().getHitboxDefaultY());
    }
}
