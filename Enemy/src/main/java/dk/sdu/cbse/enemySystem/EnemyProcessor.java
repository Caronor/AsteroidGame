package dk.sdu.cbse.enemySystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyProcessor implements IEntityProcessingService {
    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) entity;

            if (enemy.getHealth() <= 0) {
                world.removeEntity(enemy);
                continue;
            }

            if (random.nextDouble() < 0.05) {
                enemy.setRotation(random.nextInt(360));
            }

            //Move forward
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            enemy.setX(enemy.getX() + changeX * 0.5);
            enemy.setY(enemy.getY() + changeY * 0.5);

            //Wrap around screen
            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);
            }
            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            }
            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }
            if (random.nextDouble() < 0.02) {
                shootBullet(enemy, gameData, world);
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