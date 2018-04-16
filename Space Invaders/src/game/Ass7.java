package game;

import animations.AnimationRunner;
import biuoop.GUI;
/**
 * @author 204225148
 * @version 4.0
 * @since 2017-06-14
 */
public class Ass7 {
    /**
     * This method is the main method that mainley
     * runs the game.
     * @param args is the array of arguments that are needed
     * in order to run the program.
     */
    public static void main(String [] args) {

        GUI gui = new GUI("Space Invadores", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);

        GameMenu gameMenu = new GameMenu(gui, runner);
        gameMenu.runMenu(new LevelnformationSetter());
    }
}
