package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

public class AsteroidSplitter implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity entity, World world) {
        if (entity.getRadius() < 5) {
            //Too small to split, remove from world
            world.removeEntity(entity);
            return;
        }

        float newRadius = entity.getRadius() / 2;

        //Create two smaller asteroids
        Entity asteroid1 = new Asteroid();
        Entity asteroid2 = new Asteroid();

        asteroid1.setX(entity.getX() + 5);
        asteroid1.setY(entity.getY() + 5);
        asteroid2.setX(entity.getX() - 5);
        asteroid2.setY(entity.getY() - 5);

        asteroid1.setRadius(newRadius);
        asteroid2.setRadius(newRadius);

        asteroid1.setRotation(entity.getRotation() + 20); // Slightly different direction
        asteroid2.setRotation(entity.getRotation() - 20);

        world.addEntity(asteroid1);
        world.addEntity(asteroid2);
    }
}
