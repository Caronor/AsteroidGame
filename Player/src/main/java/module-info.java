import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;
    requires spring.context;
    requires spring.beans;

    exports dk.sdu.cbse.playerSystem to spring.beans, spring.context, spring.core;

    uses dk.sdu.cbse.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.cbse.playerSystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.playerSystem.PlayerControlSystem;
}