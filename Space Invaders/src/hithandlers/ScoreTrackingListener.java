package hithandlers;
import gameobjects.AbstractBlock;
import gameobjects.Ball;

/**
* @author 204225148
* @version 3.0
* @since 2017-05-29
* */
public class ScoreTrackingListener implements HitListener {
    //Property.
    private Counter currentScore;

    /**
     * This method is the constractor of the score tracking lisener.
     * @param scoreCounter is the actual counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }

    @Override
    /**
     * This method notify the liseners about hitting between the block
     * and the ball.
     * @param beingHit is the block that is being hit.
     * @param hitter is the ball which is the hitter.
     */
    public void hitEvent(AbstractBlock beingHit, Ball hitter) {
        this.currentScore.increase(beingHit.getHitPoints());
    }
 }