
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Graph extends JFrame
{

    private Button addComplexNumbers, viewNumbers, clear;
    private JPanel buttons;
    private static GraphPanel gPanel;

    public Graph()
    {
        setTitle("Graph");
        setSize(417,400);
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

        //Panels
        gPanel = new GraphPanel();
        buttons = new JPanel();
        buttons.setSize(400, 100);
        buttons.setLayout(new FlowLayout());
        buttons.add(addComplexNumbers);
        buttons.add(viewNumbers);
        buttons.add(clear);

        //Scene Setup
        add(gPanel);
        add(buttons, BorderLayout.SOUTH);
    }

    private class ButtonPress implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String action = e.getActionCommand();
            if(action.equals("Add Numbers"))
                numberInput();
            else if(action.equals("Clear Numbers"))
            {
                gPanel.clearGraph();
                gPanel.repaint();
                JOptionPane.showMessageDialog(null, "List of ComplexNumbers has been cleared", "Alert", JOptionPane.INFORMATION_MESSAGE);               
            }
            else if(action.equals("View Numbers"))
                displayNumbers();
        }
    }

    //Prompts user to enter numbers
    private static void numberInput()
    {
        ComplexNumber cNum = new ComplexNumber();
        boolean repeat, flag = true;
        String inputReal, inputImaginary;

        do{
            //Real component Assignment
            repeat = true;
            while(repeat)
            {
                try {
                    inputReal = JOptionPane.showInputDialog(null, "Enter real component below: \n(Hit cancel to exit)", "Real Component", JOptionPane.QUESTION_MESSAGE);
                    //This exits entire method if user clicks cancel button
                    if(inputReal == null)
                    {
                        flag = false;
                        return;
                    }
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
                    inputImaginary = JOptionPane.showInputDialog(null, "Enter imaginary component below (Don't include i): \n(Hit cancel to exit)", "Imaginary Component", JOptionPane.QUESTION_MESSAGE);
                    //This exits entire method if user clicks cancel button
                    if(inputImaginary == null)
                    {
                        flag = false;
                        return;
                    }
                    cNum.setImaginary(Integer.parseInt(inputImaginary));
                    repeat = false;
                } catch (NumberFormatException e) {
                    repeat = true;
                }
            }

            //Adds entered complex number to list and plots it
            gPanel.addComplexNumber(new ComplexNumber(cNum.getReal(), cNum.getImaginary()));
            gPanel.repaint();

        }while(flag);

    }

    //Shows user numbers they have entered
    private static void displayNumbers()
    {
        String numbers = "";
        //Not using a advanced for loop here because i'm using...
        //i to tell me position and when to not add comma
        for(int i = 0; i < gPanel.getComplexNumbers().size(); i++)
        {
            if(i != gPanel.getComplexNumbers().size() - 1)
                numbers += gPanel.getComplexNumbers().get(i) + ", ";
            else
                numbers += gPanel.getComplexNumbers().get(i);
        }

        JOptionPane.showMessageDialog(null, "You have " + gPanel.getComplexNumbers().size() + " complex numbers plotted:\n\t " + numbers);
    }

}