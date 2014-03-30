
import Jama.Matrix;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Doug on 3/25/2014.
 */
public class HandwritingRecognition {

    public static final int DRAWINGWIDTH = 100;
    public static final int DRAWINGHEIGHT = 100;

    public static void main(String[] args) {
        //Matrix m = new Matrix(5,5,10);
        //Pad writingPad = new Pad(25000);
        Drawing d = new Drawing();
        d.setPreferredSize(new Dimension(DRAWINGWIDTH, DRAWINGHEIGHT));

        JFrame mainWindow = new JFrame("Handwriting Recognition");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleText = new JLabel();
        titleText.setText("Handwriting Recognition");
        titleText.setPreferredSize(new Dimension(50, 25));

        mainWindow.getContentPane().add(titleText, BorderLayout.NORTH);
        mainWindow.getContentPane().add(d, BorderLayout.CENTER);

        mainWindow.pack();
        mainWindow.setVisible(true);

    }
}
