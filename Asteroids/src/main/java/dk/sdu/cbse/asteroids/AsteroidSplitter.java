package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class AsteroidSplitter implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (!entity.isCollided()) continue;

            if (entity.getRadius() < 10) {
                world.removeEntity(entity);
                return;
            }

            float newRadius = entity.getRadius() / 2;

            Entity asteroid1 = new Asteroid();
            Entity asteroid2 = new Asteroid();

            asteroid1.setRadius(newRadius);
            asteroid2.setRadius(newRadius);

            asteroid1.setX(entity.getX());
            asteroid1.setY(entity.getY());
            asteroid2.setX(entity.getX());
            asteroid2.setY(entity.getY());

            asteroid1.setPolygonCoordinates(newRadius, -newRadius, -newRadius, -newRadius, newRadius, newRadius, newRadius);
            asteroid2.setPolygonCoordinates(newRadius, -newRadius, -newRadius, -newRadius, newRadius, newRadius, newRadius);

            asteroid1.setRotation(entity.getRotation() + 15 + (float)(Math.random() * 10));
            asteroid2.setRotation(entity.getRotation() - 15 - (float)(Math.random() * 10));

            world.removeEntity(entity);
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);
        }
    }
}
