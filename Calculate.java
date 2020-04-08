
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*  
    ----TODO----
    Create Division Method, might not want to because that will mean having to deal with doubles which I am currently using ints
*/

public class Calculate extends JFrame
{
    private JLabel historyLabel;
    private JButton numberEntry, viewHistory;
    private JPanel north, south, east, west;
    private static JTextArea historyArea;

    public Calculate()
    {
        //Basic window setup
        setTitle("Calculate");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        //TextArea
        historyArea = new JTextArea();
        historyArea.setLineWrap(true);
        historyArea.setEditable(false);

        //Label
        historyLabel = new JLabel("Previously Entered: ");

        //Panel
        north = new JPanel(new FlowLayout());         
        south = new JPanel(new FlowLayout());         
        east  = new JPanel(new FlowLayout());
        west  = new JPanel(new FlowLayout());

        //Buttons
        numberEntry = new JButton("Enter Numbers");
        numberEntry.addActionListener(new ButtonPress());
        viewHistory = new JButton("View Operation History");
        viewHistory.addActionListener(new ButtonPress());

        //Staging Components
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(west, BorderLayout.NORTH);
        add(east, BorderLayout.EAST);
        south.add(numberEntry);
        west.add(historyLabel);
        west.add(historyArea);
    }

    private class ButtonPress implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Enter Numbers"))
                numberInput();
            else
                JOptionPane.showMessageDialog(null, "Nope");
        }
    }

    private static void numberInput()
    {
        //Method Objects
        ComplexNumber firstComplex = new ComplexNumber(); 
        ComplexNumber secondComplex = new ComplexNumber();
        ComplexNumber result = new ComplexNumber();
        ArrayList<ComplexNumber> complexHistory = new ArrayList<>();
        ArrayList<String> signHistory = new ArrayList<>();


        //Method variables
        boolean repeat;
        String inputReal, inputImaginary, flag, sign;

        //First Number Real Component Assignment
        repeat = true;
        while(repeat)
        {
            try {
                inputReal = JOptionPane.showInputDialog(null, "Enter real component below: ");
                firstComplex.setReal(Integer.parseInt(inputReal));
                repeat = false;
            } catch (Exception e) {
                repeat = true;
            }
        }


        //First Number Imaginary Component Assignment
        repeat = true;
        while(repeat)
        {
            try {
                do
                {
                    inputImaginary = JOptionPane.showInputDialog(null, "Enter imaginary component below (Don't include i): ");
                    if(inputImaginary.contains("i"))
                        JOptionPane.showMessageDialog(null, "Don't include i please :(", "Big no no", JOptionPane.ERROR_MESSAGE);
                    repeat = false;
                }while(inputImaginary.contains("i"));
                firstComplex.setImaginary(Integer.parseInt(inputImaginary));
            } catch (Exception e) {
                repeat = true;
            }
        
        }

        //Keeping track of entered complex number to generate history list
        //Annonymous object because firstComplex changes and just adding itself just adds a
        //reference to it's memory so history wouldnt be accurate as it is changing
        complexHistory.add(new ComplexNumber(firstComplex.getReal(), firstComplex.getImaginary()));

        //Operation
        do
        {
            sign = JOptionPane.showInputDialog(null, "Enter (+-*/) for operation between next complex number");
        }while(!sign.equals("+") && !sign.equals("-") && !sign.equals("*") && !sign.equals("/"));
        
        //Keeping track of entered sign to generate history list
        signHistory.add(sign);

        //Second Number Real Component Assignment
        repeat = true;
        while(repeat)
        {
            try {
                inputReal = JOptionPane.showInputDialog(null, "Enter real component below: ");
                secondComplex.setReal(Integer.parseInt(inputReal));
                repeat = false;
            } catch (Exception e) {
                repeat = true;
            }
        }


        //Second Number Imaginary Component Assignment
        repeat = true;
        while(repeat)
        {
            try {
                //Do-while detects if i is being used to generate specific message
                do
                {
                    inputImaginary = JOptionPane.showInputDialog(null, "Enter imaginary component below (Don't include i): ");
                    if(inputImaginary.contains("i"))
                        JOptionPane.showMessageDialog(null, "Don't include i please :(", "Big no no", JOptionPane.ERROR_MESSAGE);
                    repeat = false;
                }while(inputImaginary.contains("i"));
                secondComplex.setImaginary(Integer.parseInt(inputImaginary));
            } catch (Exception e) {
                repeat = true;
            }
        
        }

        //Keeping track of entered complex number to generate history list
        complexHistory.add(new ComplexNumber(secondComplex.getReal(), secondComplex.getImaginary()));


        //Calling method based on inputted operation
        if(sign.equals("+"))
            result = complexAddition(firstComplex, secondComplex);
        else if(sign.equals("-"))
            result = complexSubtraction(firstComplex, secondComplex);
        else if(sign.equals("*"))
            result = complexMultiplication(firstComplex, secondComplex);
        
        //If statement here just keeps the imaginary sign, prevents the program from saying +-
        if(String.valueOf(result.getImaginary()).charAt(0) == '-')
            flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + result.getImaginary() + "i" + "\nType y if you would like to continue or anything else if not");
        else
            flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + "+" + result.getImaginary() + "i" + "\nType y if you would like to continue or anything else if not");

        //This section executes as long as user wishes to continue
        while(flag.equalsIgnoreCase("y"))
        {
            //Carrying over previous result because this is continuing from there using this as first term
            firstComplex.setReal(result.getReal());
            firstComplex.setImaginary(result.getImaginary());

            //Sign between numbers
            do
            {
                sign = JOptionPane.showInputDialog(null, "Enter (+-*/) for operation between next complex number");
            }while(!sign.equals("+") && !sign.equals("-") && !sign.equals("*") && !sign.equals("/"));
            
            //Adding sign to history
            signHistory.add(sign);

            //Second Number Real Assignment
            repeat = true;
            while(repeat)
            {
                try {
                    inputReal = JOptionPane.showInputDialog(null, "Enter real component below: ");
                    secondComplex.setReal(Integer.parseInt(inputReal));
                    repeat = false;
                } catch (Exception e) {
                    repeat = true;
                }
            }


            //Second Number Imaginary Component Assignment
            repeat = true;
            while(repeat)
            {
                try {
                    //Do-while detects if i is being used to generate specific message
                    do
                    {
                        inputImaginary = JOptionPane.showInputDialog(null, "Enter imaginary component below (Don't include i): ");
                        if(inputImaginary.contains("i"))
                            JOptionPane.showMessageDialog(null, "Don't include i please :(", "Big no no", JOptionPane.ERROR_MESSAGE);
                        repeat = false;
                    }while(inputImaginary.contains("i"));
                    secondComplex.setImaginary(Integer.parseInt(inputImaginary));
                } catch (Exception e) {
                    repeat = true;
                }
            }
            
            //Adding to complexNumber history
            complexHistory.add(new ComplexNumber(secondComplex.getReal(), secondComplex.getImaginary()));

            //Determines which computational method to call based on operation chosen by user
            if(sign.equals("+"))
                result = complexAddition(firstComplex, secondComplex);
            else if(sign.equals("-"))
                result = complexSubtraction(firstComplex, secondComplex);
            else if(sign.equals("*"))
                result = complexMultiplication(firstComplex, secondComplex);

            //If statement here just keeps the imaginary sign, prevents the program from saying +-
            if(String.valueOf(result.getImaginary()).charAt(0) == '-')
                flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + result.getImaginary() + "i" + "\nType y if you would like to continue or anything else if not");
            else
                flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + "+" + result.getImaginary() + "i" + "\nType y if you would like to continue or anything else if not");
        }

        //Adds the final result to history so user can know what the answer to their entered numbers after exiting
        signHistory.add("=");
        complexHistory.add(result);

        //Generating final history String
        String history = "";
        for(int i = 0; i < complexHistory.size(); i++)
        {
            history += complexHistory.get(i);
            if(i < signHistory.size())
                history += signHistory.get(i);
        }

        historyArea.setText(history);
    }

    private static ComplexNumber complexAddition(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        ComplexNumber output = new ComplexNumber();
        output.setReal(cNum1.getReal() + cNum2.getReal());
        output.setImaginary(cNum1.getImaginary() + cNum2.getImaginary());
        return output;
    }

    private static ComplexNumber complexSubtraction(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        ComplexNumber output = new ComplexNumber();
        output.setReal(cNum1.getReal() - cNum2.getReal());
        output.setImaginary(cNum1.getImaginary() - cNum2.getImaginary());
        return output;
    }

    private static ComplexNumber complexMultiplication(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        ComplexNumber output = new ComplexNumber();
        output.setReal((cNum1.getReal() * cNum2.getReal()) + (cNum1.getImaginary() * cNum2.getImaginary() * -1));
        output.setImaginary((cNum1.getReal() * cNum2.getImaginary()) + (cNum2.getReal() * cNum1.getImaginary()));
        return output;
    }


}