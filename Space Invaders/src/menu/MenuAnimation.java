package menu;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import animations.Animation;
import grafics.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @param <T>
 *            The return value of the menu options.
 */
public class MenuAnimation<T> implements Menu<T>, Animation {
    private List<Option<T>> optionsList;
    private String          menuTitle;
    private KeyboardSensor  keyboard1;
    private boolean         stop;
    private Option<T>       selectedOption1;
    private Sprite          backRound;

    /**
     * This method is the constructor of the menu Animation. All of it's members
     * initialized by parameters.
     *
     * @param menuTitlee
     *            is the title of the menu.
     * @param keyboard2
     *            is the key to be pressed when choosing an option.
     * @param sprite
     *            is the background of the Menu screen.
     */
    public MenuAnimation(String menuTitlee, KeyboardSensor keyboard2,
            Sprite sprite) {
        this.menuTitle = menuTitlee;
        this.keyboard1 = keyboard2;
        this.backRound = sprite;
        this.stop = false;
        this.selectedOption1 = null;
        this.optionsList = new LinkedList<Option<T>>();
    }

    @Override
    /**
     * This method creates a new option from the arguments. /// add to list??
     *
     * @param key
     *            is the key being pressed by selecting.
     * @param message
     *            is the message being transfered when choosing.
     * @param returnVal
     *            is the value being returned when selecting.
     */
    public void addSelection(String key, String message, T returnVal) {
        Option<T> newOption = new Option<T>(key, message, returnVal);
        this.optionsList.add(newOption);
    }

    @Override
    /**
     * This method returns the selectedOption's 'returnVal'.
     *
     * @return the selectedOption's 'returnVal'
     */
    public T getStatus() {
        return this.selectedOption1.getReturnVal();
    }

    /**
     * This function draws one frame.
     *
     * @param d
     *            is the draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.backRound.drawOn(d);
        d.setColor(Color.CYAN);
        d.drawText(200, d.getHeight() / 2 - 60, this.menuTitle, 36);
        int textHight = 0;
        // Printing the options
        for (Option<T> option : this.optionsList) {
            d.drawText(200, d.getHeight() / 2 + textHight * 40,
                    "Press " + option.getKey() + " to " + option.getMessage(),
                    30);
            textHight++;
        }

        // Checking if one of the option has been selected.
        for (Option<T> option : this.optionsList) {
            if (this.keyboard1.isPressed(option.getKey())) {
                this.selectedOption1 = option;
                this.stop = true;
            }
        }
    }

    /**
     * This method tells if the frame should be continued or stopped.
     *
     * @return boolean true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    /**
     * The Method restarts the animation.
     */
    public void restartAnimation() {
        this.stop = false;
    }

    @Override
    /**
     * This method creates a new option from a sub menu, and add if the the
     * menu's option list.
     *
     * @param key
     *            is the key being pressed by selecting.
     * @param message
     *            is the message being transfered when choosing.
     * @param subMenu
     *            The sub menu to add.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.addSelection(key, message, subMenu.getStatus());
    }
}