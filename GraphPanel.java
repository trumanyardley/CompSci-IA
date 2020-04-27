import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
---TODO---
Add coordinates above each plotted point
*/

public class GraphPanel extends JPanel
{

    private final static int WIDTH = 400;
    private final static int HEIGHT = 320;
    private ArrayList<ComplexNumber> complexNumbers;

    public GraphPanel()
    {
        setSize(WIDTH, HEIGHT);
        complexNumbers = new ArrayList<>();
    }

    public ArrayList<ComplexNumber> getComplexNumbers()
    {
        return complexNumbers;
    }

    public void addComplexNumber(ComplexNumber cNum)
    {
        complexNumbers.add(cNum);
    }

    public void clearGraph()
    {
        complexNumbers.clear();
    }

    //Draws x and y axis
    public void paint(Graphics g)
    {
        super.paint(g);
        //X and Y axis
        g.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
        //Axis Labels
        g.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        g.drawString("i", WIDTH/2-15, 15);
        g.drawString("r", 5, HEIGHT/2-10);
        //Little lines that indicate max values
        g.drawLine(10, HEIGHT/2-10, 10, HEIGHT/2+10);
        g.drawLine(WIDTH-10, HEIGHT/2-10, WIDTH-10, HEIGHT/2+10);
        g.drawLine(WIDTH/2-10, 10, WIDTH/2+10, 10);
        g.drawLine(WIDTH/2-10, HEIGHT-10, WIDTH/2+10, HEIGHT-10);


        //Only bothers if list exists
        if(complexNumbers.size() > 0)
        {
            int maxReal = 1;
            int maxImaginary = 1;

            for(ComplexNumber c : complexNumbers)
            {
                //Finding max real and imaginary numbers to use for incrementing when plotting
                //Math.abs is important here so negative numbers can also become max even though...
                //they're negative
                if(Math.abs(c.getReal()) > maxReal)
                    maxReal = Math.abs(c.getReal());
                if(Math.abs(c.getImaginary()) > maxImaginary)
                    maxImaginary = Math.abs(c.getImaginary());
            }

            double realIncrement = 0.0;
            if(maxReal != 0)
                realIncrement = (WIDTH/2.0-10)/maxReal;
            double imaginaryIncrement = 0.0;
            if(maxImaginary != 0)
                imaginaryIncrement =  (HEIGHT/2.0-10)/maxImaginary;

            //Draw Max values for each point of axis, LEFT, RIGHT, TOP, Bottom drawn respectively
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(String.valueOf(maxReal), 5, HEIGHT/2+30);
            g.drawString("-" + String.valueOf(maxReal), WIDTH - 20 - (10 * (String.valueOf(maxReal).length() - 1)), HEIGHT/2+30);
            g.drawString(String.valueOf(maxImaginary), WIDTH/2+15, 15);
            g.drawString("-" + String.valueOf(maxImaginary), WIDTH/2+15, HEIGHT);

            for(ComplexNumber c : complexNumbers)
            {   
                int xPos;
                int yPos;
                if(c.getReal() >= 0)
                    xPos = (WIDTH) / 2 -  (int) (realIncrement * c.getReal()) - 3;
                else
                    xPos = (WIDTH) / 2 +  (int) (realIncrement * c.getReal() * -1) - 3;

                if(c.getImaginary() >= 0)
                    yPos = (HEIGHT) / 2 - (int) (imaginaryIncrement * c.getImaginary()) - 3;
                else
                    yPos = (HEIGHT) / 2 +  (int) (imaginaryIncrement * c.getImaginary() * -1) - 3;

                g.fillOval(xPos, yPos, 6, 6);
            }


        }
    }
    

}

