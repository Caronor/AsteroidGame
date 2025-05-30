package dk.sdu.cbse.playerSystem;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerControlSystemTest {
    private PlayerControlSystem playerControlSystem;
    private GameData gameData;
    private World world;
    private GameKeys gameKeys;
    private Player player;

    @BeforeEach
    void setUp() {
        playerControlSystem = new PlayerControlSystem();

        gameData = mock(GameData.class);
        world = mock(World.class);
        gameKeys = mock(GameKeys.class);
        player = new Player();

        when(gameData.getKeys()).thenReturn(gameKeys);
        when(world.getEntities(Player.class)).thenReturn(Collections.singletonList(player));
    }

    @Test
    void testUpMovement() {
        when(gameKeys.isDown(GameKeys.UP)).thenReturn(true);
        when(gameData.getDisplayHeight()).thenReturn(800);
        when(gameData.getDisplayWidth()).thenReturn(800);

        player.setRotation(0);
        player.setX(100);
        player.setY(100);

        double startX = player.getX();
        double startY = player.getY();

        playerControlSystem.process(gameData, world);

        assertTrue(player.getX() > startX);
        assertEquals(startY, player.getY(), 0.001);
    }

    @Test
    void testRightMovement() {
        when(gameKeys.isDown(GameKeys.RIGHT)).thenReturn(true);

        double initialRotation = player.getRotation();
        playerControlSystem.process(gameData, world);

        assertEquals(initialRotation + 5, player.getRotation(), 0.001);
    }

    @Test
    void testLeftMovement() {
        when(gameKeys.isDown(GameKeys.LEFT)).thenReturn(true);

        double initialRotation = player.getRotation();
        playerControlSystem.process(gameData, world);

        assertEquals(initialRotation - 5, player.getRotation(), 0.001);
    }

    @Test
    void testPlayerStayWithinBounds() {
        when(gameData.getDisplayHeight()).thenReturn(800);
        when(gameData.getDisplayWidth()).thenReturn(800);

        player.setX(-10);
        player.setY(900);

        playerControlSystem.process(gameData, world);

        assertEquals(1, player.getX(), 0.001);
        assertEquals(799, player.getY(), 0.001);
    }

    @Test
    void testPlayerCollision() {
        player.setHealth(3);
        player.setCollided(true);

        playerControlSystem.process(gameData, world);

        assertEquals(2, player.getHealth());
    }

    @Test
    void testPlayerCollisionRemovesPlayer() {
        player.setHealth(1);
        player.setCollided(true);

        playerControlSystem.process(gameData, world);

        verify(world).removeEntity(player);
    }
}