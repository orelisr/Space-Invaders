package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author 204225148
 * @version 4.0
 * @since
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decoreatedAniation;
    private KeyboardSensor keyboard;
    private String         stopKey;
    private boolean        stop;
    private boolean        isAlreadyPressed;

    /**
     * A constractor that ceates a from a KeyboardSensor, String, and Animation.
     *
     * @param sensor
     *            - The game's KeyboardSensor.
     * @param key
     *            - The key that makes the animation to stop when it is pressed.
     * @param animation
     *            - The animation the KeyPressStoppableAnimation will decorate.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.keyboard = sensor;
        this.stopKey = key;
        this.decoreatedAniation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    /**
     * This method prints one frame of the pause screen.
     *
     * @param d
     *            is the method that the screen will be printed on.
     */
    public void doOneFrame(DrawSurface d) {
        this.decoreatedAniation.doOneFrame(d);

        // If the stopKey is pressed and it was not pressed before the animation
        // have started.
        if ((this.keyboard.isPressed(this.stopKey))
                && (!this.isAlreadyPressed)) {
            this.stop = true;
            // If the stopKey  is not pressed.
        } else if (!this.keyboard.isPressed(this.stopKey)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    /**
     * This method tells if the frame should continue or stop.
     *
     * @return a boolean tells if the frame should continue or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The Method restarts the animation.
     */
    public void restartAnimation() {
        this.stop = false;
    }
}