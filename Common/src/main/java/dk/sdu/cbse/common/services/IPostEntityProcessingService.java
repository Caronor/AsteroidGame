package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * The {@code IPostEntityProcessingService} interface defines a contract for processing
 * game entities after the main update cycle. This is used for collision detection.
 */
public interface IPostEntityProcessingService {
    /**
     * Processes game entities after the main update cycle.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *   <li>{@code gameData} must not be {@code null}.</li>
     *   <li>{@code world} must not be {@code null}.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *   <li>Entities in the {@code world} may be updated or modified.</li>
     *   <li>The {@code gameData} object may be modified.</li>
     * </ul>
     *
     * @param gameData The game data containing input states, display settings, and other information.
     * @param world The game world containing all entities.
     */
    void process(GameData gameData, World world);
}
