package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.AsteroidSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

public class AsteroidSplitter implements AsteroidSPI {
    @Override
    public void createSplitAsteroid(Entity entity, World world) {
        if (entity.getRadius() < 10) {
            world.removeEntity(entity);
            return;
        }

        world.removeEntity(entity);

        float newRadius = entity.getRadius() / 2;

        Entity asteroid1 = new Asteroid();
        Entity asteroid2 = new Asteroid();

        asteroid1.setRadius(newRadius);
        asteroid2.setRadius(newRadius);

        asteroid1.setPolygonCoordinates(newRadius, -newRadius, -newRadius, -newRadius, newRadius, newRadius, newRadius);
        asteroid2.setPolygonCoordinates(newRadius, -newRadius, -newRadius, -newRadius, newRadius, newRadius, newRadius);

        asteroid1.setRotation(entity.getRotation() + 15 + (float)(Math.random() * 10));
        asteroid2.setRotation(entity.getRotation() - 15 - (float)(Math.random() * 10));

        world.addEntity(asteroid1);
        world.addEntity(asteroid2);
    }
}
