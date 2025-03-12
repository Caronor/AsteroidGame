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
    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> toRemove = new ArrayList<>();

        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                //if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // Collision Detection
                if (this.collides(entity1, entity2)) {
                    // If a bullet hits an asteroid, split the asteroid instead of removing it
                    if (entity1 instanceof Bullet && entity2 instanceof Asteroid) {
                        handleAsteroidSplit(entity2, world);
                        toRemove.add(entity1); // Remove bullet
                        toRemove.add(entity2); // Remove asteroid (replaced by split ones)
                    } else if (entity2 instanceof Bullet && entity1 instanceof Asteroid) {
                        handleAsteroidSplit(entity1, world);
                        toRemove.add(entity1); // Remove asteroid
                        toRemove.add(entity2); // Remove bullet
                    } else {
                        // Default behavior: remove both entities
                        toRemove.add(entity1);
                        toRemove.add(entity2);
                    }
                }
            }
        }
    }

    private void handleAsteroidSplit(Entity asteroid, World world) {
        IAsteroidSplitter splitter = getAsteroidSplitter();
        if (splitter != null) {
            splitter.createSplitAsteroid(asteroid, world);
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    private IAsteroidSplitter getAsteroidSplitter() {
        for (IAsteroidSplitter splitter : ServiceLoader.load(IAsteroidSplitter.class)) {
            return splitter; // Return the first found implementation
        }
        return null;
    }
}
