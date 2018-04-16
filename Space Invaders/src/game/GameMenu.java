package game;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import animations.Animation;
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameobjects.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import highscores.HighScoresAnimation;
import highscores.HighScoresTable;
import menu.Menu;
import menu.MenuAnimation;
import menu.Task;
/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class GameMenu {

    private HighScoresTable scoresTable;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private GUI gameGui;
    private Menu<Task<Void>> menu;

    /**
     * This method is the constructor of the class initializing the properties
     * that are given to the game menu.
     *
     * @param gui
     *            is the gui surface.
     * @param animationRunner
     *            is the animation runner of the menu.
     */
    public GameMenu(GUI gui, AnimationRunner animationRunner) {
        this.gameGui = gui;
        this.keyboard = this.gameGui.getKeyboardSensor();
        this.runner = animationRunner;
        this.scoresTable = this.loadHighScoreTable("highscores");
    }

    /**
     * This method will run the games' menu.
     *
     * @param level
     *            The level the game will run.
     */
    public void runMenu(LevelnformationSetter level) {
        this.menu = this.createMenu(level);
        while (true) {
            while (true) {
                runner.run(menu);
                Task<Void> task = this.menu.getStatus();
                task.run();
            }
        }
    }

    /**
     * This method load the highscore table from the file its name provided.
     *
     * @param fileName
     *            The highscore table file name.
     * @return The HighScoresTable is loaded from the file.
     */
    private HighScoresTable loadHighScoreTable(String fileName) {
        // Load the highScoreTable
        HighScoresTable highScoresGame = new HighScoresTable(10);
        File scoresFileName = new File(fileName);

        // If the scoresFileName is an existing file
        if (scoresFileName.exists()) {
            try {
                highScoresGame.load(scoresFileName);
            } catch (IOException e) {
                System.err.println("There was a problem with loading the high scorses files.");
            }
        } else {
            try {
                highScoresGame.save(scoresFileName);
            } catch (IOException e) {
                System.out.println("There was a problem with saveing the the high sorces file");
            }
        }
        return highScoresGame;
    }

    /**
     * Creates the games's menu.
     *
     * @param level
     *            The level the game will run.
     * @return the task that creates the menu.
     */
    private Menu<Task<Void>> createMenu(LevelnformationSetter level) {
        Block blackBlock = new Block(new Rectangle(new Point(0, 0), 800, 600));
        blackBlock.setColor(Color.BLACK);

        // Creates The menu
        MenuAnimation<Task<Void>> menuu = new MenuAnimation<>("Space Invadors",
                this.keyboard, blackBlock);

        // Add the run game option.
        final GameFlow gf = new GameFlow(runner, this.keyboard,
                this.gameGui, this.scoresTable);

        final LevelnformationSetter finalLevel = level;

        menuu.addSelection("s", "Start Game", new Task<Void>() {
            @Override
            public Void run() {
                gf.runLevels(finalLevel);
                return null;
            }
        });

        // Add the show highScoreOption.
        final Animation finalAnimation = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.scoresTable, blackBlock));
        final AnimationRunner finalRunner = runner;
        menuu.addSelection("h", "see the high scores", new Task<Void>() {
            public Void run() {
                finalRunner.run(finalAnimation);
                return null;
            }
        });

        // Add the quit option
        final HighScoresTable finalTable = this.scoresTable;
        final String finalFileName = "highscores";
        menuu.addSelection("q", "quit", new Task<Void>() {
            public Void run() {
                File file = new File(finalFileName);
                try {
                    finalTable.save(file);
                } catch (IOException e) {
                    System.out.println("The is a problem with saving the score file");
                }

                System.exit(0);
                return null;
            }
        });

        return menuu;
    }
}