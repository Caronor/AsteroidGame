package dk.sdu.cbse.bulletsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet))
        }
    }
}