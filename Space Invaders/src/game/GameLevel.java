package game;

import grafics.Sprite;
import grafics.SpriteCollection;
import grafics.ScoreIndicator;
import grafics.LivesIndicator;
import grafics.LevelNameIndecation;
import animations.AnimationRunner;
import animations.CountdownAnimation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.Animation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamelogicobjects.BulletCreator;
import gamelogicobjects.Collidable;
import gamelogicobjects.GameEnvironment;
import gameobjects.Spaceship;
import gameobjects.DeathBlock;
import gameobjects.AbstractBlock;
import gameobjects.Alien;
import gameobjects.AlienFormation;
import gameobjects.Ball;
import gameobjects.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hithandlers.BallRemover;
import hithandlers.BlockRemover;
import hithandlers.Counter;
import hithandlers.FormationLisener;
import hithandlers.HitListener;
import hithandlers.ScoreTrackingListener;

/**
 * @author 204225148
 * @version 1.0
 * @since 2017-04-10
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Spaceship spaceship;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private Counter alignCounter;
    private Counter scoreCounter;
    private Block informationBlock;
    private BlockRemover alienRmv;
    private BlockRemover shiledBlocksRemover;
    private BallRemover bllRmv;
    private ScoreTrackingListener scoreTrack;
    private ScoreIndicator labelScore;
    private LivesIndicator lableLifes;
    private Sprite backRoundPic;
    private LevelInformation levelInfo;
    private LevelNameIndecation levelname;
    private BulletCreator bullCreator;
    private AlienFormation aligns;
    private IsAlive isAlive;

    /**
     * This method initialize the game lists of sprite - the things that the
     * balls can hit and also the environment of the game.
     *
     * @param level
     *            The level the Game level will run.
     * @param ks
     *            The keyboard associated with the game gui.
     * @param ar
     *            The animation runner that runs the game animation.
     * @param guii
     *            The game gui.
     * @param score
     *            The counter that keeps the games's score
     * @param lifes
     *            The counter that keeps the user's number of lifes.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, GUI guii, Counter score,
            Counter lifes) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.scoreCounter = score;
        this.labelScore = new ScoreIndicator(this.scoreCounter, java.awt.Color.BLACK);
        this.sprites.addSprite(labelScore);
        this.lableLifes = new LivesIndicator(lifes, java.awt.Color.BLACK);
        this.sprites.addSprite(this.lableLifes);
        this.levelInfo = level;
        this.backRoundPic = this.levelInfo.getBackground();
        Rectangle rect = new Rectangle(new Point(0, 0), 800, 30);
        this.informationBlock = new Block(rect, Color.WHITE, 1);
        this.keyboard = ks;
        this.runner = ar;
        this.gui = guii;
        this.levelname = new LevelNameIndecation(this.levelInfo.levelName(), java.awt.Color.BLACK);
        this.sprites.addSprite(levelname);
        this.bullCreator = new BulletCreator(this, new Rectangle(new Point(0, 0), this.gui.getDrawSurface().getWidth(),
                this.gui.getDrawSurface().getHeight()), this.environment);
        this.aligns = new AlienFormation(30, 770, 530, this.levelInfo.formationSpeed(), this.bullCreator,
                this.levelInfo.numberOfAlienRows());
        this.isAlive = new IsAlive();
        this.bullCreator = new BulletCreator(this, new Rectangle(new Point(0, 0), 800, 600), this.environment);
    }

    /**
     * This method adds an alien to the formation of aliens.
     *
     * @param row
     *            is the row of the formation that the alien will be added to.
     * @param column
     *            is the column of the formation that the alien will be added
     *            to.
     */
    public void addAlien(int row, int column) {
        this.aligns.addAlien(row, column);
    }

    /**
     * This method removes the alien from the formation of the aliens.
     *
     * @param ali
     *            is the alien to be removed from the formation.
     */
    public void removAlien(Alien ali) {
        this.aligns.removeAlien(ali);
    }

    /**
     * This method add to the list that the balls can hit a collidible item that
     * the balls can hit as well to the enviorment of the game.
     *
     * @param c
     *            - is the item that the balls can collide with
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method removes the provided collidable from the game.
     *
     * @param c
     *            - The collidable will be removed from the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method initialize the game lists of sprite - the things that the
     * balls can hit and also the enviroment of the game.
     *
     * @param s
     *            - is the sprite that the balls can collide with.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method removes the provided sprite from the game.
     *
     * @param s
     *            - The sprite will be removed from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method initialize all the variables that the game will use such us
     * bloc1ks, balls, borders, number of hits, paddle and enviorment.
     */
    public void initialize() {
        // Set the spaceship
        int middle = (800 - this.levelInfo.paddleWidth()) / 2;

        Rectangle rec = new Rectangle(new Point(middle, 550), this.levelInfo.paddleWidth(), 20);
        this.spaceship = new Spaceship(rec, this.levelInfo.paddleSpeed(), 760 - this.levelInfo.paddleWidth(), 40, gui,
                java.awt.Color.LIGHT_GRAY, this.bullCreator);
        spaceship.addToGame(this);

        // Return the environment to it's initial state.
        this.alignCounter = new Counter(this.levelInfo.numberOfAliens());

        // Set the alien remover
        this.alienRmv = new BlockRemover(this, this.alignCounter);

        // Set the scoreTrack
        this.scoreTrack = new ScoreTrackingListener(this.scoreCounter);

        // set the thick borders
        Rectangle recUp = new Rectangle(new Point(0, -10), 800, 60);
        DeathBlock blockUp = new DeathBlock(recUp);
        blockUp.setColor(null);
        blockUp.addToGame(this);

        Rectangle recDown = new Rectangle(new Point(30, 599), 800, 50);
        DeathBlock blockDown = new DeathBlock(recDown);
        blockDown.setColor(null);
        blockDown.addToGame(this);

        Rectangle recRight = new Rectangle(new Point(-20, 30), 50, 570);
        DeathBlock blockRight = new DeathBlock(recRight);
        blockRight.setColor(null);
        blockRight.addToGame(this);

        Rectangle recLeft = new Rectangle(new Point(770, 30), 50, 570);
        DeathBlock blockLeft = new DeathBlock(recLeft);
        blockLeft.setColor(null);
        blockLeft.addToGame(this);

        // Set the ball remover
        this.bllRmv = new BallRemover(this);

        // Make the ball remover listen to the death block.
        blockDown.addHitListener(this.bllRmv);
        blockUp.addHitListener(this.bllRmv);
        blockRight.addHitListener(this.bllRmv);
        blockLeft.addHitListener(this.bllRmv);

        // Adds the aliens to the game.
        for (Point alienPosition : this.levelInfo.alienPossitions()) {
            Alien eurnio = this.aligns.addAlien((int) alienPosition.getX(), (int) alienPosition.getY());
            eurnio.addToGame(this);
            eurnio.addHitListener(this.alienRmv);
            eurnio.addHitListener(this.scoreTrack);
            eurnio.addHitListener(this.bllRmv);
        }

        this.sprites.addSprite(this.aligns);

        // Set the shield blocks
        this.shiledBlocksRemover = new BlockRemover(this, null);
        int blockOfBlocksXStartCoord = 100;
        int blockOfBlocksYStartCoord = 490;
        int spaceBetweenBlockOfBlock = 250;
        int blockSize = 5;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 30; k++) {
                    Point p = new Point(blockOfBlocksXStartCoord + spaceBetweenBlockOfBlock * i + blockSize * k,
                            blockOfBlocksYStartCoord + j * blockSize);
                    Rectangle rect = new Rectangle(p, blockSize, blockSize);
                    Block block = new Block(rect, Color.CYAN, 1);
                    this.addCollidable(block);
                    this.addSprite(block);
                    block.addHitListener(this.shiledBlocksRemover);
                    block.addHitListener(this.bllRmv);
                }
            }
        }

        // Set the animation
        this.running = true;
        this.runner = new AnimationRunner(this.gui, 60);
    }

    /**
     * The method keeps playing the game until all the blocks are removes or the
     * user lost all of his balls.
     */
    public void playOneTurn() {
        List<Ball> ballsToRemove = new ArrayList<Ball>();
        // Save all the bullets to a list.
        for (Sprite sprite : this.sprites.getSptitesLists()) {
            if (sprite instanceof Ball) {
                ballsToRemove.add((Ball) sprite);
            }
        }

        // Remove all the bullets from the game.
        for (Ball bullet : ballsToRemove) {
            bullet.removeFromGame(this);
        }

        // Return the aliens to their original place.
        this.aligns.restartLocationAndSpeed();

        // Set the paddle
        int middle = (800 - this.levelInfo.paddleWidth()) / 2;
        this.spaceship.moveTo(middle);

        // Make is alive to listen to the spaceship and the alien formation.
        this.spaceship.addHitListener(this.isAlive);
        this.aligns.addFormationLisener(this.isAlive);

        // Count down
        Block blackBlock = new Block(new Rectangle(new Point(0, 0), 800, 600));
        blackBlock.setColor(Color.BLACK);
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, blackBlock));

        // Set the game as running and run the game
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.backRoundPic.drawOn(d);
        this.informationBlock.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(this.runner.getDt());

        // If the game have to stop because all the aliens have killed.
        if (this.alignCounter.getValue() == 0) {
            this.running = false;
        }

        // If the player want to stop the game
        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.backRoundPic);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pause));
        }
    }

    @Override
    /**
     * The method returns if the game level animation should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The method returns is the level has Completed.
     *
     * @return If the level has completed.
     */
    public boolean isComplete() {
        if (this.alignCounter.getValue() == 0) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * This method restarts the animation.
     */
    public void restartAnimation() {
    }

    /**
     * @author 204225148
     * @version 1.0
     * @since 2017-07-03
     */
    private class IsAlive implements HitListener, FormationLisener {
        @Override
        /**
         * This method is listening to a ball hitting the block
         * and removes it from the game if being hitted.
         * @param beingHit is the block that is being hitted.
         * @param hitter is the ball that hits the block.
         */
        public void hitEvent(AbstractBlock beingHit, Ball hitter) {
            hitter.removeFromGame(GameLevel.this);
            running = false;
        }

        @Override
        /**
         * This method stops the running of the level when the formation
         * of aliens stops moving.
         * @param foration is the formation of aliens.
         */
        public void formationStoppedMovigEvent(AlienFormation foration) {
            running = false;
        }
    }
}