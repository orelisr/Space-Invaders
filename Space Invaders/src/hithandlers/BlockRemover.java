package hithandlers;
import game.GameLevel;
import gameobjects.AbstractBlock;
import gameobjects.Ball;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class BlockRemover implements HitListener {
    // Properties
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * This method is the contractor of the block remover.
     * Initializing the current game that the block takes place at
     * and the number of the blocks that are still available in the game.
     * @param gamee is the current game.
     * @param remainingBlocks is the number of blocks that are
     * still in the game.
     */
    public BlockRemover(GameLevel gamee, Counter remainingBlocks) {
        this.game = gamee;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    /** This method causes that Blocks that are hit and reach
     * 0 hit-points should be removed
     * from the game.
     * @param beingHit is the current block than is being hit.
     * @param hitter is the ball itself which is the hitter.*/
    public void hitEvent(AbstractBlock beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 0) {
            beingHit.removeFromGame(game);
            if (this.remainingBlocks != null) {
                this.remainingBlocks.decrease(1);
            }
        }
    }
}