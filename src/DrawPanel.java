//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    /**
     * My painting class that draws the background onto the frame
     */
    DrawPanel() {
        this.setPreferredSize(new Dimension(1400, 1400));
    }

    /**
     * paints the transparent squares onto the back gorund of the frame.
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        int side = 38;
        int gap = 42;
        Graphics2D g2D = (Graphics2D)g;
        g2D.setStroke(new BasicStroke(2.0F));
        g2D.setPaint(new Color(0, 0, 0, 7));
        int currentPosY = -25;
        int currentPosX = -25;

        for(int i = 0; i < 50; ++i) {
            for(int j = 0; j < 50; ++j) {
                g2D.drawRect(currentPosX, currentPosY, side, side);
                currentPosY += gap;
            }

            currentPosY = 0;
            currentPosX += gap;
        }

    }
}
