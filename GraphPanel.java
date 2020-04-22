import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;

/*
---TODO---
use Graph's static complexNumber arrayList to actually 
develop grid increments and to plot those points
*/

public class GraphPanel extends JPanel
{

    public GraphPanel()
    {
        setSize(400, 300);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(200, 0, 200, 400);
    }
    
}

