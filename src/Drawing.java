import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The drawing class extends JPanel and can be added (is added) as a component of the main application window.
 * It has an Image and a Graphics
 * The Graphics holds the Image's Graphics
 * The Image is initialized by creating a filled dark gray rectangle.
 *
 * JPanel's paint function is overrode such that it will initialize the Image if that has not been done
 * Then it will draw the image at the top left corner.
 *
 * processMouseEvent and processMouseMotionEvent are overrode.
 * processMouseEvent only checks if the mouse was just clicked,
 * setting Drawing's X and Y to the click location. If the mouse
 * was not just clicked, it does nothing.
 *
 * processMouseMotionEvent handles the event in which the mouse is
 * being dragged. If the mouse is not being dragged, it does nothing.
 * If it is, it draws a line between Drawing's X and Y to the x and y
 * of the MouseEvent.
 *
 * The line drawwed during consecutive mouse drag events is white to
 * be distinguised from the dark gray over which it is drawwed.
 *
 * Then Drawing's inherited JPanel Graphics member is updated with the new
 * version of the Image what belongs to the Drawing object.
 *
 */
public class Drawing extends JPanel {
    private Image image;
    private Graphics graphics;
    private int X = -1;
    private int Y = -1;

    public Drawing() {
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.COMPONENT_EVENT_MASK);
        // this.setSize(500, 500);
    }

    private void __Image() {
        this.image = createImage(getWidth(), getHeight());
        this.graphics = this.image.getGraphics();
        this.graphics.setColor(Color.DARK_GRAY);
        this.graphics.fillRect(0,0,getWidth(), getHeight());
    }

    @Override
    public void paint(final Graphics g) {
        if(this.graphics == null) {
            __Image();
        }
        g.drawImage(this.image, 0, 0, this);
    }

    @Override
    protected void processMouseEvent(final MouseEvent e) {
        if (e.getID() != MouseEvent.MOUSE_PRESSED) {
            return;
        }
        this.X = e.getX();
        this.Y = e.getY();
    }

    @Override
    protected void processMouseMotionEvent(final MouseEvent e) {
        if (e.getID() != MouseEvent.MOUSE_DRAGGED) {
            return;
        }

        this.graphics.setColor(Color.WHITE);
        //this.graphics.drawString("LOL", X, Y);
        //this.graphics.draw3DRect(this.X, this.Y, 20, 20, true);
        //this.graphics.drawOval(this.X, this.Y, 2, 10);
        this.graphics.drawLine(this.X, this.Y, e.getX(), e.getY());
        getGraphics().drawImage(this.image, 0, 0, this);
        this.X = e.getX();
        this.Y = e.getY();
    }
}
