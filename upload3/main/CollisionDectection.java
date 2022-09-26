package main;

import gameChar.Entity;

public class CollisionDectection {

    private final GamePanel gPanel;

    public CollisionDectection(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void check(Entity entity) {
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
                int obstacle1 = gPanel.getTileMng().getTileMap()[entityBotRow][entityLeftCol];
                int obstacle2 = gPanel.getTileMng().getTileMap()[entityBotRow][entityRightCol];
                if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
            case 1 -> {
                entityTopRow = (entityTopPosY - entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.getTileMng().getTileMap()[entityTopRow][entityLeftCol];
                int obstacle2 = gPanel.getTileMng().getTileMap()[entityTopRow][entityRightCol];
                if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
            case 2 -> {
                entityLeftCol = (entityLeftPosX - entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.getTileMng().getTileMap()[entityTopRow][entityLeftCol];
                int obstacle2 = gPanel.getTileMng().getTileMap()[entityBotRow][entityLeftCol];
                if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
            case 3 -> {
                entityRightCol = (entityRightPosX + entity.getSpeed()) / gPanel.getActualTileSz();
                int obstacle1 = gPanel.getTileMng().getTileMap()[entityTopRow][entityRightCol];
                int obstacle2 = gPanel.getTileMng().getTileMap()[entityBotRow][entityRightCol];
                if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
                    entity.setCollided(true);
                }
            }
        }
    }
}
