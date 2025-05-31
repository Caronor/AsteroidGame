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
        player = mock(Player.class);

        when(gameData.getKeys()).thenReturn(gameKeys);
        when(world.getEntities(Player.class)).thenReturn(Collections.singletonList(player));
    }

    @Test
    void testPlayerHealth() {
        when(player.getHealth()).thenReturn(0);

        playerControlSystem.process(gameData, world);

        verify(world, times(1)).removeEntity(player);
    }

    @Test
    void testUpMovement() {
        when(player.getHealth()).thenReturn(1);
        when(gameKeys.isDown(GameKeys.UP)).thenReturn(true);
        when(player.getRotation()).thenReturn(0.0);
        when(player.getX()).thenReturn(100.0);
        when(player.getY()).thenReturn(100.0);

        playerControlSystem.process(gameData, world);

        verify(player).setX(101.0);
        verify(player).setY(100.0);
    }

    @Test
    void testRightMovement() {
        when(player.getHealth()).thenReturn(1);
        when(gameKeys.isDown(GameKeys.RIGHT)).thenReturn(true);
        when(player.getRotation()).thenReturn(0.0);

        playerControlSystem.process(gameData, world);

        verify(player).setRotation(5.0);
    }

    @Test
    void testLeftMovement() {
        when(player.getHealth()).thenReturn(1);
        when(gameKeys.isDown(GameKeys.LEFT)).thenReturn(true);
        when(player.getRotation()).thenReturn(0.0);

        playerControlSystem.process(gameData, world);

        verify(player).setRotation(-5.0);
    }

    @Test
    void testPlayerStayWithinBounds() {
        when(player.getHealth()).thenReturn(1);
        when(gameData.getDisplayHeight()).thenReturn(800);
        when(gameData.getDisplayWidth()).thenReturn(800);

        when(player.getX()).thenReturn(-10.0).thenReturn(810.0);
        when(player.getY()).thenReturn(-5.0).thenReturn(900.0);

        playerControlSystem.process(gameData, world);

        verify(player).setX(1.0);
        verify(player).setY(1.0);

        when(player.getX()).thenReturn(810.0);
        when(player.getY()).thenReturn(900.0);
        playerControlSystem.process(gameData, world);
        verify(player, atLeastOnce()).setX(799.0);
        verify(player, atLeastOnce()).setY(799.0);
    }
}