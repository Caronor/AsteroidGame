package dk.sdu.cbse.enemySystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyProcessor implements IEntityProcessingService {
    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemyShip : world.getEntities(Enemy.class)) {
            if (random.nextDouble() < 0.05) {
                enemyShip.setRotation(random.nextInt(360));
            }

            //Move forward
            double changeX = Math.cos(Math.toRadians(enemyShip.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemyShip.getRotation()));
            enemyShip.setX(enemyShip.getX() + changeX);
            enemyShip.setY(enemyShip.getY() + changeY);

            //Wrap around screen
            if (enemyShip.getX() < 0) {
                enemyShip.setX(1);
            }
            if (enemyShip.getX() > gameData.getDisplayWidth()) {
                enemyShip.setX(gameData.getDisplayWidth() - 1);
            }
            if (enemyShip.getY() < 0) {
                enemyShip.setY(1);
            }
            if (enemyShip.getY() > gameData.getDisplayHeight()) {
                enemyShip.setY(gameData.getDisplayHeight() - 1);
            }
            if (random.nextDouble() < 0.03) {

            }
        }
    }

    private void shootBullet(Entity enemyShip, GameData gameData, World world) {
        for (BulletSPI bulletSPI : getBulletSPIs()) {
            Entity bullet = bulletSPI.createBullet(enemyShip, gameData);
            if (bullet != null) {
                world.addEntity(bullet);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
