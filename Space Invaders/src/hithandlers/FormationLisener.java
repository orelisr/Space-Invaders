package hithandlers;

import gameobjects.AlienFormation;
/**
 * @author 204225148
 * @version 4.0
 * @since 2017-06-14
 */
public interface FormationLisener {
    /**
     * This method listens to an event when the formation
     * of aliens stops to move.
     * @param foration is the formation of aliens.
     */
    void formationStoppedMovigEvent(AlienFormation foration);
}
