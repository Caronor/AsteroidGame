package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

@Component
public class AsteroidProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            if (asteroid.isCollided()) {
                AsteroidSplitter splitter = new AsteroidSplitter();
                splitter.createSplitAsteroid(asteroid, world);
            }

            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            // Screen wrapping logic
            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }
            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }
            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }
            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }
        }
    }
}
