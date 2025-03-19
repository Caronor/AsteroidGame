package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * The {@code IEntityProcessingService} interface is for processing game entities during the update cycle.
 * Implementations of this interface modify the game world and its entities based on the current game state.
 */
public interface IEntityProcessingService {
    /**
     * Processes game entities by updating the state of entities in
     * the game world based on the provided game data.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *   <li>The {@code gameData} parameter must not be {@code null}.</li>
     *   <li>The {@code world} parameter must not be {@code null}.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *   <li>Entities in the {@code world} may be updated, removed, or added.</li>
     *   <li>The {@code gameData} object may be modified (e.g., key states updated).</li>
     * </ul>
     *
     * @param gameData The current game data, including input states and display settings.
     * @param world The game world containing all entities.
     */
    void process(GameData gameData, World world);
}
