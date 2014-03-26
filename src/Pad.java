/**
 * Created by kb on 3/26/14.
 */
import javax.swing.*;
import java.awt.*;


public class Pad {
    JComponent.PaintingCanvas
    public Pad(int num_pixels){
        int[] char_data = new int[num_pixels];

        //Create window
        JFrame mainWindow = new JFrame("Whoa?");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create canvas
        Canvas charCanvas = new Canvas();
        charCanvas.setSize(500, 500);
        Graphics paintBrush;
        //paintBrush.setColor();
        charCanvas.paint();

        //Add canvas to jframe
        mainWindow.getContentPane().add(charCanvas, BorderLayout.CENTER);
        mainWindow.pack();
        mainWindow.add(charCanvas);
        mainWindow.setVisible(true);

        mainWindow.add(new JComponent() {
        })

    }
}
