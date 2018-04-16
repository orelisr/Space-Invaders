package highscores;

import animations.Animation;
import biuoop.DrawSurface;
import grafics.Sprite;

/**
 * @author 204225148
 * @version 4.0
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scoresTable;
    private Sprite          backRound;

    /**
     * This method is the counstractor of the HighScoresAnimation.
     *
     * @param scores
     *            the highScoreTable to be present.
     * @param backgroung is the background of the animation.
     */
    public HighScoresAnimation(HighScoresTable scores, Sprite backgroung) {
        this.scoresTable = scores;
        this.backRound = backgroung;
    }

    @Override
    /**
     * This method prints one frame of the HishScoresAnination screen.
     *
     * @param d
     *            is the method that the screen will be printed on.
     */
    public void doOneFrame(DrawSurface d) {
        this.backRound.drawOn(d);
        d.setColor(java.awt.Color.CYAN);
        d.drawText(d.getWidth() / 2 - 50, 100, "High Scores!", 32);
        d.drawText(d.getWidth() / 2 - 100, 140, "Name", 24);
        d.drawText(d.getWidth() / 2, 140, "Score", 24);
        int lineSpaces = 2;
        for (ScoreInfo score : this.scoresTable.getHighScores()) {
            d.drawText(d.getWidth() / 2 - 100, 140 + lineSpaces * 20,
                    score.getName(), 20);
            d.drawText(d.getWidth() / 2, 140 + lineSpaces * 20,
                    String.valueOf(score.getScore()), 20);
            lineSpaces++;
        }
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 4 * 3,
                "Press space to continue", 25);
    }

    @Override
    /**
     * This method tells if the animation should stop or not.
     *
     * @return a boolean which tells if the animation should stop or not.
     */
    public boolean shouldStop() {
        return false;
    }

    @Override
    /**
     * This method restart the animation of the high score animation.
     */
    public void restartAnimation() {
    }
}
