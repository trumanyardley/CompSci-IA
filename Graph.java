
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.util.ArrayList;
import java.awt.event.*;

/*
---TODO---
flag input exception handling
Graph button proper functionality
*/
public class Graph extends JFrame
{

    private Button addComplexNumbers, viewNumbers, clear, graph;
    private JPanel buttons;
    private GraphPanel gPanel;
    private static ArrayList<ComplexNumber> complexNumbers = new ArrayList<>();

    public Graph()
    {
        setTitle("Graph");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        //Buttons
        addComplexNumbers = new Button("Add Numbers");
        addComplexNumbers.addActionListener(new ButtonPress());
        viewNumbers = new Button("View Numbers");
        viewNumbers.addActionListener(new ButtonPress());
        clear = new Button("Clear Numbers");
        clear.addActionListener(new ButtonPress());
        graph = new Button("Graph");

        //Panels
        gPanel = new GraphPanel();
        buttons = new JPanel();
        buttons.setSize(400, 100);
        buttons.setLayout(new FlowLayout());
        buttons.add(addComplexNumbers);
        buttons.add(viewNumbers);
        buttons.add(clear);
        buttons.add(graph);

        //Scene Setup
        add(gPanel);
        add(buttons, BorderLayout.SOUTH);
    }

    private class ButtonPress implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Add Numbers"))
                numberInput();
            else if(e.getActionCommand().equals("Clear Numbers"))
                clearNumbers();
            else if(e.getActionCommand().equals("View Numbers"))
                displayNumbers();
        }
    }

    private static void numberInput()
    {
        ComplexNumber cNum = new ComplexNumber();
        boolean repeat;
        String inputReal, inputImaginary, flag = "y";

        do{
            //Real component Assignment
            repeat = true;
            while(repeat)
            {
                try {
                    inputReal = JOptionPane.showInputDialog(null, "Enter real component below: ");
                    //This exits entire method if user clicks cancel button
                    if(inputReal == null)
                        return;
                    cNum.setReal(Integer.parseInt(inputReal));
                    repeat = false;
                } catch (NumberFormatException e) {
                    repeat = true;
                }
            }

            //Imaginary Component Assignment
            repeat = true;
            while(repeat)
            {
                try {
                    inputImaginary = JOptionPane.showInputDialog(null, "Enter imaginary component below (Don't include i): ");
                    //This exits entire method if user clicks cancel button
                    if(inputImaginary == null)
                        return;
                    cNum.setImaginary(Integer.parseInt(inputImaginary));
                    repeat = false;
                } catch (NumberFormatException e) {
                    repeat = true;
                }
            }

            //Adds entered complex number to list to plot
            complexNumbers.add(cNum);

            flag = JOptionPane.showInputDialog(null, "Type y if you would like to add another complex number");
            
            //If statement assumes that if user clicks cancel they are finished...
            //and prevents program from panicking
            if(flag == null)
                flag = " ";

        }while(flag.equalsIgnoreCase("y"));

    }

    private static void clearNumbers()
    {
        complexNumbers.clear();
        JOptionPane.showMessageDialog(null, "List of ComplexNumbers has been cleared", "Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayNumbers()
    {
        String numbers = "";
        //Not using a advanced for loop here because i'm using...
        //i to tell me position and when to not add comma
        for(int i = 0; i < complexNumbers.size(); i++)
        {
            if(i != complexNumbers.size() - 1)
                numbers += complexNumbers.get(i) + ", ";
            else
                numbers += complexNumbers.get(i);
        }

        JOptionPane.showMessageDialog(null, "You have " + complexNumbers.size() + " complex numbers listed:\n\t " + numbers);
    }

}