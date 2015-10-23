package utilities;

import java.awt.*;

/**
 * Interface for selectable game objects.
 * @author Robert Wilk
 *         Created on 3/26/2015.
 */
public interface ISelectable {

    /**
     * Sets the selection state.
     * @param selected Is it selected?
     */
    void setSelected(boolean selected);

    /**
     * Determines the selected state.
     * @return Is it selected?
     */
    boolean isSelected();

    /**
     * Determines if the selectable contains a point.
     * @param point The point.
     * @return Does it contain the point?
     */
    boolean contains(Point point);
}
