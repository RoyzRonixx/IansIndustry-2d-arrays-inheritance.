//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class UIComponentLibrary extends JFrame {
    public UIComponentLibrary() {
    }


    /**
     * For creating a button and adding it to the frame.
     * @param name name of the buttone
     * @param sizeX Width of the button
     * @param sizeY Height of the button
     * @param posX Pixels from the left of the frame
     * @param posY Pixels from the top of the frame
     * @param listener The frame's action listener
     * @param frame The current frame
     * @param layout The current frame's SprintLayout
     * @return The button object
     */
    public static JButton createAButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, Frame frame, SpringLayout layout) {
        JButton myButton = new JButton(name);
        myButton.addActionListener(listener);
        myButton.setPreferredSize(new Dimension(sizeX, sizeY));
        layout.putConstraint("West", myButton, posX, "West", frame);
        layout.putConstraint("North", myButton, posY, "North", frame);
        frame.add(myButton);
        return myButton;
    }

    /**
     * For creating a button and adding it to the frame that is positioned relative to another button.
     * @param name Label on the button
     * @param sizeX Width of hte button
     * @param sizeY Height of the button
     * @param posX Pixels left of another buttons
     * @param posY Pixels below another button
     * @param listener The frame's action listener
     * @param frame The current frame
     * @param layout The current frame's SprintLayout
     * @param button The Button object used as an anchor to the frame.
     * @return The button Object
     */
    public static JButton createAButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, Frame frame, SpringLayout layout, JButton button) {
        JButton myButton = new JButton(name);
        myButton.addActionListener(listener);
        myButton.setPreferredSize(new Dimension(sizeX, sizeY));
        layout.putConstraint("West", myButton, posX, "West", button);
        layout.putConstraint("North", myButton, posY, "North", button);
        frame.add(myButton);
        return myButton;
    }

    /**
     * Creates a text field
     * @param size how many chars wide the text box is
     * @param posX Pixels from the left of the frame
     * @param posY Pixels from the top of the frame
     * @param frame The current frame
     * @param layout The current frame's SprintLayout
     * @return The text field
     */
    public static JTextField createATextField(int size, int posX, int posY, Frame frame, SpringLayout layout) {
        JTextField myTextField = new JTextField(size);
        layout.putConstraint("West", myTextField, posX, "West", frame);
        layout.putConstraint("North", myTextField, posY, "North", frame);
        frame.add(myTextField);
        return myTextField;
    }

    /**
     *  Creates a Text area
     * @param cols Width of the text area in columns
     * @param rows Height of the text area in rows
     * @param posX Pixels from the left of the frame
     * @param posY Pixels from the top of the frame
     * @param frame The current frame
     * @param layout The current frame's SprintLayout
     * @return The Text Area
     */
    public static JTextArea createATextArea(int cols, int rows, int posX, int posY, Frame frame, SpringLayout layout) {
        JTextArea myTextArea = new JTextArea(rows, cols);
        layout.putConstraint("West", myTextArea, posX, "West", frame);
        layout.putConstraint("North", myTextArea, posY, "North", frame);
        frame.add(myTextArea);
        myTextArea.setBorder(new LineBorder(Color.BLACK));
        return myTextArea;
    }

    /**
     * Creates a label and adds it to the frame
     * @param text the text inside the label
     * @param posX Pixels from the left of the frame
     * @param posY Pixels from the top of the frame
     * @param frame The current frame
     * @param layout The current frame's SprintLayout
     * @return The Label
     */
    public static JLabel createALabel(String text, int posX, int posY, Frame frame, SpringLayout layout) {
        JLabel myLabel = new JLabel(text);
        layout.putConstraint("West", myLabel, posX, "West", frame);
        layout.putConstraint("North", myLabel, posY, "North", frame);
        frame.add(myLabel);

        return myLabel;
    }

    /**
     * Creates a label that is positioned compared to a given text field
     * @param text text in the label
     * @param posX how far west of the text field
     * @param posY how far south of the text field
     * @param frame the frame the label will be added to
     * @param layout the frames sprint layout
     * @param textField the text field that the label is connected too.
     * @return a label
     */
    public static JLabel createALabel(String text, int posX, int posY, Frame frame, SpringLayout layout, JTextField textField) {
        JLabel myLabel = new JLabel(text);
        layout.putConstraint("West", myLabel, posX, "West", textField);
        layout.putConstraint("North", myLabel, posY, "North", textField);

        frame.add(myLabel);
        return myLabel;

    }

    /**
     * Creates a label that is positioned relative to a given lable
     * @param text the taxe in the label
     * @param posX how far west of the text field
     * @param posY how far south of the text field
     * @param frame the frame the label will be added to
     * @param layout the spring layout of the frame
     * @param label the parent label
     * @return a label
     */
    public static JLabel createALabel(String text, int posX, int posY, Frame frame, SpringLayout layout, JLabel label) {
        JLabel myLabel = new JLabel(text);
        layout.putConstraint("West", myLabel, posX, "West", label);
        layout.putConstraint("North", myLabel, posY, "North", label);
        myLabel.setOpaque(true);
        myLabel.setBackground(new Color(0,0,0,20));
        myLabel.setPreferredSize(new Dimension(100, 30));
        myLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(myLabel);
        return myLabel;
    }

    /**
     * Creates grid piece object, A modified text field that will hold values and a mouse listener
     * @param size the square side's length
     * @param posX Pixels from the left of the frame
     * @param posY Pixels from the top of the frame
     * @param frame the frame the grid is added to
     * @param layout the layout of the frame
     * @return a grid piece
     */
    public static GridPiece createGridPiece(int size, int posX, int posY, Frame frame, SpringLayout layout) {
        GridPiece gridPiece = new GridPiece();
        gridPiece.setEditable(false);
        gridPiece.setBorder(new LineBorder(Color.BLACK, 2));
        gridPiece.setPreferredSize(new Dimension(size, size));
        layout.putConstraint("West", gridPiece, posX, "West", frame);
        layout.putConstraint("North", gridPiece, posY, "North", frame);
        frame.add(gridPiece);
        return gridPiece;
    }

    /**
     * create the warning legend in the window using repetitive sizes
     * @param text the text in the label
     * @param posX the position on the X axis of the frame
     * @param posY  the position on the Y axis of the frame
     * @param frame the frame for this to be added to
     * @param layout the spring layout of the frame.
     * @return the danger label
     */
    public static JLabel createDangerLabel(String text, int posX, int posY, Frame frame, SpringLayout layout){
        JLabel myLabel = new JLabel(text);
        layout.putConstraint("West", myLabel, posX, "West", frame);
        layout.putConstraint("North", myLabel, posY, "North", frame);
        frame.add(myLabel);

        myLabel.setOpaque(true);
        myLabel.setPreferredSize(new Dimension(150,30));
        myLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myLabel.setVerticalAlignment(SwingConstants.CENTER);
        return myLabel;
    }

}
