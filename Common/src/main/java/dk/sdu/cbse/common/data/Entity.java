package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private int health;
    private boolean isCollided;

    public String getID() {
        return ID.toString();
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public float getRadius() {
        return radius;
    }

    public int getHealth() {
        return health;
    }

    public boolean isCollided() {
        return isCollided;
    }

    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }
}
