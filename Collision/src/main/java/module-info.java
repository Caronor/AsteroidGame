import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    uses dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.cbse.collisionSystem.CollisionDetector;
}