package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * The {@code IGamePluginService} interface defines methods for initializing
 * and removing game entities when the game starts and stops.
 *
 * <p>
 * Implementations of this interface are responsible for setting up the necessary
 * entities when the game starts or when a plugin is loaded, and for
 * cleaning up those entities when the game ends or the plugin is removed.
 * </p>.
 */
public interface IGamePluginService {
    /**
     * Initializes and adds game entities to the world when the game or plugin starts.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *   <li>{@code gameData} must not be {@code null}.</li>
     *   <li>{@code world} must not be {@code null}.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *   <li>One or more game entities are added to the {@code world}.</li>
     *   <li>The {@code gameData} object may be modified during initialization.</li>
     * </ul>
     *
     * @param gameData The game data containing input states, display settings, and other information.
     * @param world The game world where entities will be added.
     */
    void start(GameData gameData, World world);

    /**
     * Removes game entities when the game or plugin stops.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *   <li>{@code gameData} must not be {@code null}.</li>
     *   <li>{@code world} must not be {@code null}.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *   <li>All entities added by this plugin are removed from the {@code world}.</li>
     *   <li>The {@code gameData} object may be modified during cleanup.</li>
     * </ul>
     *
     * @param gameData The game data containing input states, display settings, and other information.
     * @param world The game world from which entities will be removed.
     */
    void stop(GameData gameData, World world);
}
