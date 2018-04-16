package hithandlers;
import game.GameLevel;
import gameobjects.AbstractBlock;
import gameobjects.Ball;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class BallRemover implements HitListener {
    // Properties
    private GameLevel game;

    /**
     * This method is the contractor of the ball remover.
     * Initializing the current game the ball take place of and
     * the counter of the remaining balls in the game.
     * @param gamee is the current game.
     */
    public BallRemover(GameLevel gamee) {
        this.game = gamee;
    }

    /** This method causes that Ball to be removed when hitting the
     * block of death at the bottom of the screen.
     * from the game.
     * @param hitter is the ball itself which is the hitter.
     * @param block is the block that is hitted.
     */
    public void hitEvent(AbstractBlock block, Ball hitter) {
            hitter.removeFromGame(game);
    }
}