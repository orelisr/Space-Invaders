package game;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import highscores.HighScoresAnimation;
import highscores.HighScoresTable;
import highscores.ScoreInfo;
import hithandlers.Counter;
import gameobjects.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter livesCounter;
    private Counter scoreCounter;
    private GUI gui;
    private HighScoresTable highScoresGame;

    /**
     * This is the constractor of GameFlow.
     *
     * @param ar
     *            The AnimationRunner that will run the game's animation.
     * @param ks
     *            The keyBoard associated with the game gui.
     * @param guii
     *            The game's gui.
     * @param highScoresTable
     *            The game's high scores table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI guii, HighScoresTable highScoresTable) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.livesCounter = new Counter(4);
        this.scoreCounter = new Counter(0);
        this.gui = guii;
        this.highScoresGame = highScoresTable;
    }

    /**
     * This method runs a set of level it's got.
     *
     * @param levelInfo is the level information setter.
     */
    public void runLevels(LevelnformationSetter levelInfo) {
        this.livesCounter = new Counter(3);
        this.scoreCounter = new Counter(0);

        int levelNumber = 0;
        while (true) {
            levelNumber++;
            levelInfo.setLevelName("Battle number " + levelNumber);
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui,
                    this.scoreCounter, this.livesCounter);
            level.initialize();

            while (!level.isComplete() && this.livesCounter.getValue() > 0) {
                level.playOneTurn();

                // If the level was not completed (The player lost a life).
                if (!level.isComplete()) {
                    this.livesCounter.decrease(1);
                }
            }

            // If the player passes the level.
            if (level.isComplete()) {
                this.scoreCounter.increase(100);
                levelInfo.setFormationSpeed(levelInfo.formationSpeed() * 1.25);
            }

            // If the player has no lives
            if (this.livesCounter.getValue() == 0) {
                break;
            }
        }

        boolean isWon = true;

        // If the player lost
        if (this.livesCounter.getValue() == 0) {
            isWon = false;
        }

        // Show end screen
        EndScreen end = new EndScreen(isWon, this.scoreCounter,
                new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK, 1));
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, end));

        // Checks if the playet's score is high enough to enter the
        // high score table.
        if (this.highScoresGame.getRank(this.scoreCounter.getValue()) >= 0) {

            // Put the player in the score table
            // GUI gui = new GUI("Your Name", 150, 150);
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            System.out.println(name);
            ScoreInfo selfScore = new ScoreInfo(name, this.scoreCounter.getValue());
            this.highScoresGame.add(selfScore);
        }

        Block blackBlock = new Block(new Rectangle(new Point(0, 0), 800, 600));
        blackBlock.setColor(Color.BLACK);

        HighScoresAnimation tableDraw = new HighScoresAnimation(this.highScoresGame, blackBlock);
        this.animationRunner
                .run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, tableDraw));
    }
}