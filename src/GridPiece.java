//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

/**
 * the class that will be used to build each grid piece that contains the reading value and the passes throught the
 * text box that will display the reading value.
 */
public class GridPiece extends JTextField implements MouseListener {
    private JTextField displayTextBox;
    GridPiece boo = this;
    private int issueVal;

    /**
     * the constructor that add the mouse listener to the Grid Square/ piece
     */
    GridPiece() {
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * when the mouse enters the perimeter of the grid piece the textbox will display the reading value
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
        displayTextBox.setText(Integer.toString(issueVal));

    }

    /**
     * when the mouse leave the perimeter of the grid piece the text box will erase the value in the textbox
     * @param e
     */
    public void mouseExited(MouseEvent e) {
        this.displayTextBox.setText("");
    }

    /**
     * returns the reading values stored in the grid piece
     * @return
     */
    public int getIssueVal() {
        return this.issueVal;
    }

    /**
     * saves the reading value to the grid peice
     * @param issueVal
     */
    public void setIssueVal(int issueVal) {
        this.issueVal = issueVal;
    }

    /**
     * saves the textbox that will display the reading value to the grid piece
     * @param displayTextBox
     */
    public void setDisplayTextBox(JTextField displayTextBox) {
        this.displayTextBox = displayTextBox;
    }
}
