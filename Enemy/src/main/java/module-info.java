import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires spring.context;
    requires spring.beans;

    exports dk.sdu.cbse.enemySystem to spring.beans, spring.context, spring.core;

    uses dk.sdu.cbse.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.cbse.enemySystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemySystem.EnemyProcessor;
}