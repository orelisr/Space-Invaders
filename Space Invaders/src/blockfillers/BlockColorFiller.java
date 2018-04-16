package blockfillers;

import biuoop.DrawSurface;
import geometryprimitives.Rectangle;

import java.awt.Color;

/**
 * @author 204225148
 * @version 4.0
 * @since 2017-06-14
 */
public class BlockColorFiller implements BlockFiller {
    private Color color;

    /**
     * This method draws on the screen the color of the filler.
     *
     * @param fillColor
     *            is the color o the filler.
     */
    public BlockColorFiller(Color fillColor) {
        this.color = fillColor;
    }

    @Override
    /**
     * This method draws on the screen the fulling of rectangle.
     *
     * @param surface
     *            is the surface that the the whole frame will be showed on.
     * @param rect
     *            is the rectangle to be fulled and showed.
     */
    public void drawOn(DrawSurface surface, Rectangle rect) {
        if (this.color != null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) rect.getUpperLeft().getX(),
                    (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                    (int) rect.getHeight());
        }
    }
}
