package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
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
