package blockfillers;

import biuoop.DrawSurface;
import geometryprimitives.Rectangle;

import java.awt.Image;

/**
 * @author 204225148
 * @version 4.0
 * @since 2017-06-14
 */
public class BlockImageFiller implements BlockFiller {
    private Image image;

    /**
     * This method fill the block with image.
     *
     * @param fillImage
     *            is the image that fills the block.
     */
    public BlockImageFiller(Image fillImage) {
        this.image = fillImage;
    }

    @Override
    /**
     * This method draws on the scree the fill of the block by image.
     *
     * @param surface
     *            is the surface that it will be showed on.
     * @param rect
     *            is the rectangle to fill.
     */
    public void drawOn(DrawSurface surface, Rectangle rect) {
        surface.drawImage((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), this.image);
    }
}