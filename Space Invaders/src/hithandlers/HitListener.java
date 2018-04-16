package hithandlers;

import gameobjects.AbstractBlock;
import gameobjects.Ball;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29
* */
public  interface HitListener {
    /** This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that is being hit.
     * @param hitter is the which is the hitter.
     */
    void hitEvent(AbstractBlock beingHit, Ball hitter);
 }