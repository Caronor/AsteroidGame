import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;

    provides IGamePluginService with dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidProcessor;
}