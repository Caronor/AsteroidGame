package dk.sdu.cbse.common.enemy;

import dk.sdu.cbse.common.data.Entity;

public class Enemy extends Entity {
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
