import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonPlayer;
    requires CommonEnemy;
    requires CommonAsteroids;
    requires CommonBullet;

    provides IPostEntityProcessingService with dk.sdu.cbse.collisionSystem.CollisionDetector;
}