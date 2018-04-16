package menu;

import animations.Animation;

/**
 * @param <T>
 *            The return type of the method run.
 */
public interface Menu<T> extends Animation {
    /**
     * This method creates a new option from the arguments.
     *
     * @param key
     *            is the key being pressed by selecting.
     * @param message
     *            is the message being transfered when choosing.
     * @param returnVal
     *            is the value being returned when selecting.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * This method returns the selectedOption's 'returnVal'.
     *
     * @return the selectedOption's 'returnVal'
     */
    T getStatus();

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
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
