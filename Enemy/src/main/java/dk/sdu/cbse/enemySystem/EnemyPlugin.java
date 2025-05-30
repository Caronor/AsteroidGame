package dk.sdu.cbse.enemySystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private final Random random = new Random();
    private Entity enemy;

    public EnemyPlugin() {}

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData, world);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData, World world) {
        Enemy enemyShip = new Enemy();
        float x = random.nextFloat(gameData.getDisplayWidth());
        float y = random.nextFloat(gameData.getDisplayHeight());
        enemyShip.setX(x);
        enemyShip.setY(y);
        enemyShip.setRadius(8);
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setHealth(3);

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
