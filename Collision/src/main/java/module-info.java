import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires spring.context;

    exports dk.sdu.cbse.collisionSystem to spring.beans, spring.context, spring.core;

    provides IPostEntityProcessingService with dk.sdu.cbse.collisionSystem.CollisionDetector;
}