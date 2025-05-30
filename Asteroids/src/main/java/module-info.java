import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.asteroids.AsteroidSPI;

module Asteroid {
    requires Common;
    requires CommonAsteroids;

    provides IGamePluginService with dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidProcessor;
    provides AsteroidSPI with dk.sdu.cbse.asteroids.AsteroidSplitter;
}