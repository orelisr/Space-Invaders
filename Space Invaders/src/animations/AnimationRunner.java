package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author 204225148
 * @version 3.0
 * @since 2017-05-29
 */
public class AnimationRunner {
    private GUI     gui;
    private int     framesPerSecond;
    private Sleeper sleeper;

    /**
     * This method runs the animation of the level. Running a specific
     * animation.
     *
     * @param gui
     *            the board which is the window of the game.
     * @param framesPerSecond
     *            the number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * This method returns the time past since the last gui show.
     *
     * @return The time past since the last gui show.
     */
    public double getDt() {
        return 1 / (double) this.framesPerSecond;
    }

    /**
     * This method run the animation.
     *
     * @param animation
     *            the animation to running.
     */
    public void run(Animation animation) {
        animation.restartAnimation();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}