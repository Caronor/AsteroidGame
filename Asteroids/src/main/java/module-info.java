import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidProcessor;
    provides IAsteroidSplitter with dk.sdu.cbse.asteroids.AsteroidSplitter;
}