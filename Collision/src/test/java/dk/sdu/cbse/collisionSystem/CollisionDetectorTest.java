package dk.sdu.cbse.collisionSystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectorTest {
    private CollisionDetector detector;
    private GameData gameData;
    private World world;
    private Entity entity1;
    private Entity entity2;

    @BeforeEach
    void setUp() {
        detector = new CollisionDetector();
        gameData = new GameData();
        world = new World();

        entity1 = new Entity();
        entity1.setRadius(8);
        entity1.setHealth(3);
        entity1.setPolygonCoordinates(-5,-5,10,0,-5,5);

        entity2 = new Entity();
        entity2.setRadius(1);
        entity2.setPolygonCoordinates(-5,-5,10,0,-5,5);

        world.addEntity(entity1);
        world.addEntity(entity2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void process() {
        detector.process(gameData, world);
        //Should set isCollided attribute to True for both entities
        //The entities will then be removed in their respective modules
        assertTrue(entity1.isCollided());
        assertTrue(entity2.isCollided());
    }

    @Test
    void collides() {
        boolean collided = detector.collides(entity1, entity2);
        //Entities should return True as collided
        assertTrue(collided);
    }
}