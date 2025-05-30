import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    uses dk.sdu.cbse.common.asteroids.AsteroidSPI;
    requires Common;
    requires CommonAsteroids;
    requires CommonPlayer;
    requires CommonEnemy;
    requires CommonBullet;

    provides IPostEntityProcessingService with dk.sdu.cbse.collisionSystem.CollisionDetector;
}