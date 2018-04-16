package highscores;

import java.io.Serializable;
/**
 * @author 204225148
 * @version 4.0
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {

    /**
     * Version 1 of the object.
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int    score;

    /**
     * This constructor creates a ScoreInfo from a player name and a score.
     *
     * @param playertName
     *            (String) The player name.
     * @param points
     *            (int) The score.
     */
    public ScoreInfo(String playertName, int points) {
        this.name = playertName;
        this.score = points;
    }

    /**
     * This method returns the name of the player who got this score.
     *
     * @return The name of the player who got this score.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The method returns the score.
     *
     * @return The score.
     */
    public int getScore() {
        return this.score;
    }

    @Override
    /**
     * This method gets a ScoreInfo 'otherScore' and compare it to 'this'
     * ScoreInfo.
     *
     * @ScoreInfo (ScoreInfo)
     * @return If 'this' ScoreInfo considered higher then 'otherScore' the
     *         method returns 1. If they considered equals the method returns 0,
     *         and if 'this' considered to be lower than 'otherScore' the method
     *         returns -1.
     */
    public int compareTo(ScoreInfo otherScore) {
        ScoreInfo other = (ScoreInfo) otherScore;
        if (this.getScore() > other.getScore()) {
            return 1;
        } else if (this.getScore() == other.getScore()) {
            return 0;
        } else {
            return -1;
        }
    }
}