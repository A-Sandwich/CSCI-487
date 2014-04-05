
//import Jama.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Doug on 3/25/2014.
 */
public class HandwritingRecognition {

    public static final int DRAWINGWIDTH = 100;
    public static final int DRAWINGHEIGHT = 100;

    public static void main(String[] args) {
        //Matrix m = new Matrix(5,5,10);
        //Pad writingPad = new Pad(25000);
        final NetOps ourNetwork = new NetOps();

        final Drawing d = new Drawing();
        d.setPreferredSize(new Dimension(DRAWINGWIDTH, DRAWINGHEIGHT));

        JFrame mainWindow = new JFrame("Handwriting Recognition");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleText = new JLabel();
        titleText.setText("Handwriting Recognition");
        titleText.setPreferredSize(new Dimension(50, 25));

        mainWindow.getContentPane().add(titleText, BorderLayout.NORTH);
        mainWindow.getContentPane().add(d, BorderLayout.CENTER);

        JPanel easternSeaboard = new JPanel();
        easternSeaboard.setLayout(new BoxLayout(easternSeaboard, BoxLayout.Y_AXIS));

        final JTextField addText = new JTextField();
        mainWindow.getContentPane().add(addText, BorderLayout.SOUTH);

        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ourNetwork.createDataset(d.getNormalizedArray(), addText.getText().charAt(0));
            }
        });
        easternSeaboard.add(add);

        JButton learn = new JButton("Learn");
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ourNetwork.Learn();
            }
        });
        easternSeaboard.add(learn);

        JButton test = new JButton("Test");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        easternSeaboard.add(test);

        mainWindow.getContentPane().add(easternSeaboard, BorderLayout.EAST);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

}
