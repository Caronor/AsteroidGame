package dk.sdu.cbse.enemySystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;

    public EnemyPlugin() {}

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData, world);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData, World world) {
        Entity enemyShip = new Entity();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);

        double safeX, safeY;
        boolean isSafe;

        do {
            isSafe = true;
            safeX = Math.random() * gameData.getDisplayWidth();
            safeY = Math.random() * gameData.getDisplayHeight();

            for (Entity e : world.getEntities()) {
                double dx = safeX - e.getX();
                double dy = safeY - e.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < e.getRadius() + 50) {
                    isSafe = false;
                    break;
                }
            }
        } while (!isSafe);

        enemyShip.setX(gameData.getDisplayWidth()/2);
        enemyShip.setY(gameData.getDisplayHeight()/2);
        enemyShip.setRadius(8);

        return enemyShip;
    }
}
