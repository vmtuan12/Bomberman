package event;

import creator.gameChar.Entity;
import creator.obj.bomb.Bomb;
import main.GamePanel;

public class CollisionDectection {

    private final GamePanel gPanel;

    public CollisionDectection(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void checkMap(Entity entity) {
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
                }
            }
            case 1 -> {
                entityTopRow = (entityTopPosY - entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityTopRow][entityLeftCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityTopRow][entityRightCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
            case 2 -> {
                entityLeftCol = (entityLeftPosX - entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityTopRow][entityLeftCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityBotRow][entityLeftCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
            case 3 -> {
                entityRightCol = (entityRightPosX + entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.tileMng.getTileMap()[entityTopRow][entityRightCol];
                int obstacle2 = gPanel.tileMng.getTileMap()[entityBotRow][entityRightCol];
                if (gPanel.tileMng.getTiles()[obstacle1].isImpassable() || gPanel.tileMng.getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
        }
    }

    public int checkCollisionObj(Entity entity, boolean player) {
        int index = -1;
        for(int i = 0; i < gPanel.getObjCreator().getSz(); i++) {
            if(gPanel.getItems()[i] != null) {
                entity.setHitboxX(entity.getX() + entity.getHitbox().x);
                entity.setHitboxY(entity.getY() + entity.getHitbox().y);
                gPanel.getItems()[i].setHitboxX(gPanel.getItems()[i].getX() + gPanel.getItems()[i].getHitbox().x);
                gPanel.getItems()[i].setHitboxY(gPanel.getItems()[i].getY() + gPanel.getItems()[i].getHitbox().y);

                switch (entity.getDirection()) {
                    case 0 -> { // down
                        entity.setHitboxY(entity.getHitbox().y + entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems()[i].getHitbox())) {
                            if (gPanel.getItems()[i].isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                    case 1 -> { // up
                        entity.setHitboxY(entity.getHitbox().y - entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems()[i].getHitbox())) {
                            if (gPanel.getItems()[i].isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                    case 2 -> { // left
                        entity.setHitboxX(entity.getHitbox().x - entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems()[i].getHitbox())) {
                            if (gPanel.getItems()[i].isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                    case 3 -> { // right
                        entity.setHitboxX(entity.getHitbox().x + entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.getItems()[i].getHitbox())) {
                            if (gPanel.getItems()[i].isImpassable()) {
                                entity.setCollided(true);
                            }
                            if (player) index = i;
                        }
                    }
                }
                entity.setHitboxX(entity.getHitboxDefaultX());
                entity.setHitboxY(entity.getHitboxDefaultY());
                gPanel.getItems()[i].setHitboxX(gPanel.getItems()[i].getHitboxDefaultX());
                gPanel.getItems()[i].setHitboxY(gPanel.getItems()[i].getHitboxDefaultY());
            }
        }
        return index;
    }

    public void checkCollisionBomb(Entity entity) {
        for(int i = 0; i < gPanel.bombList.size(); i++) {
            if(gPanel.bombList.get(i) != null) {
                entity.setHitboxX(entity.getX() + entity.getHitbox().x);
                entity.setHitboxY(entity.getY() + entity.getHitbox().y);
                gPanel.bombList.get(i).setHitboxX(gPanel.bombList.get(i).getX() + gPanel.bombList.get(i).getHitbox().x);
                gPanel.bombList.get(i).setHitboxY(gPanel.bombList.get(i).getY() + gPanel.bombList.get(i).getHitbox().y);

                switch (entity.getDirection()) {
                    case 0 -> { // down
                        entity.setHitboxY(entity.getHitbox().y + entity.getSpeed());
                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
                            if(checkDown(entity,gPanel.bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                    case 1 -> { // up
                        entity.setHitboxY(entity.getHitbox().y - entity.getSpeed());
//                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
//                            entity.setCollided(true);
//                        }
                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
                            if(checkUp(entity,gPanel.bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                    case 2 -> { // left
                        entity.setHitboxX(entity.getHitbox().x - entity.getSpeed());
//                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
//                            entity.setCollided(true);
//                        }
                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
                            if(checkLeft(entity,gPanel.bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                    case 3 -> { // right
                        entity.setHitboxX(entity.getHitbox().x + entity.getSpeed());
//                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
//                            entity.setCollided(true);
//                        }
                        if (entity.getHitbox().intersects(gPanel.bombList.get(i).getHitbox())) {
                            if(checkRight(entity,gPanel.bombList.get(i))) {
                                entity.setCollided(true);
                            }
                        }
                    }
                }
                entity.setHitboxX(entity.getHitboxDefaultX());
                entity.setHitboxY(entity.getHitboxDefaultY());
                gPanel.bombList.get(i).setHitboxX(gPanel.bombList.get(i).getHitboxDefaultX());
                gPanel.bombList.get(i).setHitboxY(gPanel.bombList.get(i).getHitboxDefaultY());
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

    public void checkExplosionZone(Entity entity) {
        for(int i = 0; i < gPanel.explosionList.size(); i++) {
//            System.out.println(gPanel.explosionList.get(i).getX() + "_" +gPanel.explosionList.get(i).getY());
            entity.setHitboxX(entity.getX() + entity.getHitbox().x);
            entity.setHitboxY(entity.getY() + entity.getHitbox().y);
            gPanel.explosionList.get(i).updateHitBoxPosition();
            for(int j = 0; j < gPanel.explosionList.get(i).getDeadCross().length; j++) {
//                System.out.println(j + "-" +gPanel.explosionList.get(i).getDeadCross()[j]);
                if(entity.getHitbox().intersects(gPanel.explosionList.get(i).getDeadCross()[j])) {
                    System.out.println("chet");
                }
            }
            entity.setHitboxX(entity.getHitboxDefaultX());
            entity.setHitboxY(entity.getHitboxDefaultY());
            gPanel.explosionList.get(i).resetHitBoxPosition();
        }
    }
}
