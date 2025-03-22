package dk.sdu.cbse.collisionSystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {
    public CollisionDetector() {}

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> toRemove = new ArrayList<>();

        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // Skip if already marked for removal (prevents double splitting)
                if (toRemove.contains(entity1) || toRemove.contains(entity2)) {
                    continue;
                }

                if (this.collides(entity1, entity2)) {
                    if ((entity1 instanceof Bullet && entity2 instanceof Asteroid) ||
                            (entity2 instanceof Bullet && entity1 instanceof Asteroid)) {

                        Entity asteroid = (entity1 instanceof Asteroid) ? entity1 : entity2;
                        Entity bullet = (entity1 instanceof Bullet) ? entity1 : entity2;

                        handleAsteroidSplit(asteroid, world);
                        toRemove.add(asteroid);
                        toRemove.add(bullet);

                    } else if (entity1 instanceof Bullet && entity2 instanceof Bullet) {
                        // Ignore bullet vs bullet collisions
                        continue;
                    } else if (entity1 instanceof Asteroid && entity2 instanceof Asteroid) {
                        // Ignore asteroid vs asteroid collisions
                        continue;
                    } else if (entity1 instanceof Bullet || entity2 instanceof Bullet) {
                        Entity ship = (entity1 instanceof Bullet) ? entity2 : entity1;
                        Entity bullet = (entity1 instanceof Bullet) ? entity1 : entity2;

                        ship.setHealth(ship.getHealth() - 1);
                        toRemove.add(bullet);

                        if (ship.getHealth() <= 0) {
                            toRemove.add(ship);
                        }
                    }
                }
            }
        }

        for (Entity entity : toRemove) {
            world.removeEntity(entity);
        }
    }

    private void handleAsteroidSplit(Entity asteroid, World world) {
        IAsteroidSplitter splitter = getAsteroidSplitter();
        if (splitter == null) {
            return;
        }
        splitter.createSplitAsteroid(asteroid, world);
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    private IAsteroidSplitter getAsteroidSplitter() {
        for (IAsteroidSplitter splitter : ServiceLoader.load(IAsteroidSplitter.class)) {
            return splitter;
        }
        return null;
    }
}
