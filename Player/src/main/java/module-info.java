import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module dk.sdu.cbse.common.player.Player {
    requires Common;
    requires CommonPlayer;
    requires CommonBullet;

    uses dk.sdu.cbse.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.cbse.playerSystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.playerSystem.PlayerControlSystem;
}