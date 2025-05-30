package dk.sdu.cbse.common.player;

import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity {
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
