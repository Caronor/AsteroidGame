package dk.sdu.cbse.collisionSystem;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.AsteroidSPI;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.player.Player;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionDetector implements IPostEntityProcessingService {
    public CollisionDetector() {}

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                //If entities are identical, collision is ignored
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // CollisionDetection
                if (collides(entity1, entity2)) {

                    // Asteroid and dk.sdu.cbse.common.player.Player collision
                    if (entity1 instanceof Asteroid && entity2 instanceof Player) {
                        ((Player) entity2).setHealth(0);
                        return;
                    }

                    // Enemy and dk.sdu.cbse.common.player.Player collision
                    if (entity1 instanceof Enemy && entity2 instanceof Player) {
                        ((Player) entity2).setHealth(0);
                        return;
                    }

                    if (entity1 instanceof Bullet) {
                        // Bullet and dk.sdu.cbse.common.player.Player collision
                        if (entity2 instanceof Player) {
                            ((Player) entity2).setHealth(((Player) entity2).getHealth() - 1);
                        }

                        // Bullet and Enemy collision
                        if (entity2 instanceof Enemy) {
                            ((Enemy) entity2).setHealth(((Enemy) entity2).getHealth() - 1);
                        }

                        // Bullet and Asteroid collision
                        if (entity2 instanceof Asteroid) {
                            getAsteroidSPIs().stream().findFirst().ifPresent(asteroidSPI -> asteroidSPI.createSplitAsteroid(entity2, world));
                        }
                        world.removeEntity(entity1);
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    private Collection<? extends AsteroidSPI> getAsteroidSPIs() {
        return ServiceLoader.load(AsteroidSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}