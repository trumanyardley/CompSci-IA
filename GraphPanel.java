import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
---TODO---
Add coordinates above each plotted point
Some sort of offset so the maximum values don't go into the very corners of graph
*/

public class GraphPanel extends JPanel
{

    private final static int WIDTH = 400;
    private final static int HEIGHT = 320;

    public GraphPanel()
    {
        setSize(WIDTH, HEIGHT);
    }

    //Draws x and y axis
    public void paint(Graphics g)
    {
        super.paint(g);
        //X and Y axis
        g.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);

        ArrayList<ComplexNumber> complexNumbers = Graph.getComplexNumbers();

        if(complexNumbers.size() > 0)
        {
            //Finding max number so find out what I should increment by
            int maxReal = complexNumbers.get(0).getReal();
            int maxImaginary = complexNumbers.get(0).getImaginary();

            for(ComplexNumber c : complexNumbers)
            {
                //Finding max real and imaginary numbers to use for incrementing when plotting
                //Math.abs is important here so negative numbers can also become max even though
                //they're negative
                if(c.getReal() > maxReal)
                    maxReal = Math.abs(c.getReal());
                if(c.getImaginary() > maxImaginary)
                    maxImaginary = Math.abs(c.getImaginary());
            }
            //Truncate and not Math.round because rounding up could cause graph to be too small and points not fitting
            int realIncrement = 0;
            if(maxReal != 0)
                realIncrement = (WIDTH/2)/maxReal;
            int imaginaryIncrement = 0;
            if(maxImaginary != 0)
                imaginaryIncrement =  (HEIGHT/2)/maxImaginary;

            //-3 is to offset by radius of circle so it is centered
            for(ComplexNumber c : complexNumbers)
            {   
                int xPos;
                int yPos;
                if(c.getReal() >= 0)
                    xPos = WIDTH / 2 - (realIncrement * c.getReal()) - 3;
                else
                    xPos = WIDTH / 2 + (realIncrement * c.getReal() * -1) - 3;

                if(c.getImaginary() >= 0)
                    yPos = HEIGHT / 2 - (imaginaryIncrement * c.getImaginary()) - 3;
                else
                    yPos = HEIGHT / 2 + (imaginaryIncrement * c.getImaginary() * -1) -3;

                    g.fillOval(xPos, yPos, 6, 6);
            }

            System.out.println(maxReal);
            System.out.println(maxImaginary);
        }
    }
    

}

