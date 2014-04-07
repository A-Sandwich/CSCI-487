import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.Arrays;

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
 * be distinguished from the dark gray over which it is drawwed.
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
    private int maxX = 0;
    private int maxY = 0;
    private int minX = 0;
    private int minY = 0;
    private int[][] input;
    private int[][] croppedInput;
    private int[][] normalized;

    private int mouseWiggle;

    public Drawing() {
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.COMPONENT_EVENT_MASK);
        // this.setSize(500, 500);
        input = new int[HandwritingRecognition.DRAWINGWIDTH][HandwritingRecognition.DRAWINGHEIGHT];
        for(int i = 0;i<HandwritingRecognition.DRAWINGWIDTH;i++){
            for(int j=0;j<HandwritingRecognition.DRAWINGHEIGHT;j++){
                input[i][j] = 0;
            }
        }

        normalized = new int[7][5];
        for(int i=0; i< 7; i++){
            for(int j=0;j<5;j++){
                normalized[i][j] = 0;
            }
        }


        mouseWiggle = 0;
    }

    private void __Image() {
        this.image = createImage(getWidth(), getHeight());
        this.graphics = this.image.getGraphics();
        this.graphics.setColor(Color.DARK_GRAY);
        this.graphics.fillRect(0, 0, getWidth(), getHeight());
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
        int oldX;
        int oldY;
        int currentX;
        int currentY;
        if (e.getID() != MouseEvent.MOUSE_DRAGGED) {
            return;
        }

        this.graphics.setColor(Color.WHITE);

        this.graphics.drawLine(this.X, this.Y, e.getX(), e.getY());
        getGraphics().drawImage(this.image, 0, 0, this);
        oldX = this.X;
        oldY = this.Y;
        this.X = e.getX();
        this.Y = e.getY();
        currentX = oldX;
        currentY = oldY;

        input[this.X][this.Y] = 1;

        while(X!=currentX && Y!=currentY){
            if(X > oldX)
                currentX++;
            else if(X<oldX)
                currentX--;

            if(Y>oldY)
                currentY++;
            else if(Y< oldY)
                currentY--;

            input[currentX][currentY] = 1;
        }

        if(this.X > maxX)
            maxX = this.X;
        if(this.X < minX || minX == 0)
            minX = this.X;
        if(this.Y > maxY)
            maxY = this.Y;
        if(this.Y < minY || minY == 0)
            minY = this.Y;

    }

    void generateNormalization(){
        int pixelSizeWidth = (maxX-minX)/5;
        int pixelSizeHeight = (maxY-minY)/7;

        normalized = new int[7][5];
        for(int i = 0; i < (maxY - minY)-pixelSizeHeight; i += pixelSizeHeight) {
            for(int j = 0; j < (maxX - minX)-pixelSizeWidth; j += pixelSizeWidth) {
                for(int k = i; k < i  + pixelSizeHeight; k++) {
                    for(int l = j; l < j + pixelSizeWidth; l++) {
                        if(croppedInput[k][l] == 1) {
                            //if (i < (maxY - minY) && j < (maxX - minX))
                                normalized[i/pixelSizeHeight][j/pixelSizeWidth] = 1;
                        }
                    }
                }
            }
        }

        for(int i =0;i<7;i++){
            for(int j=0; j<5; j++){
                System.out.print(normalized[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println();

    }

    void imageToArray(){
        croppedInput = new int[(maxY-minY)+1][(maxX-minX)+1];
        for(int j=minY;j<=maxY;j++){
            for(int i = minX;i<=maxX;i++){
                croppedInput[j-minY][i-minX] = input[j][i];
            }
        }

        
        for(int j=0; j<(maxY-minY);j++){
            for(int i=0;i<(maxX-minX);i++){
                System.out.print(croppedInput[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getNormalizedArray(){
        imageToArray();
        generateNormalization();
        return normalized;
    }

    public void resetDrawing(){
        
        minX = 0;
        maxX = 0;
        minY = 0;
        maxY = 0;
    }
}
