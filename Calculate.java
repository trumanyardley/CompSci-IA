import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*  
    ----TODO----
    Create Division Method, might not want to because that will mean having to deal with doubles which I am currently using ints
    Result output detect imaginary number sign, currently outputs something like +-3i
*/

public class Calculate extends JFrame
{

    private JButton numberEntry, viewHistory;
    private JPanel south, west;
    
    public Calculate()
    {
        setTitle("Calculate");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        //Panel
        south = new JPanel(new FlowLayout());         
        west  = new JPanel(new FlowLayout());

        //Buttons
        numberEntry = new JButton("Enter Numbers");
        numberEntry.addActionListener(new ButtonPress());
        viewHistory = new JButton("View Operation History");
        viewHistory.addActionListener(new ButtonPress());

        //Staging Components
        add(south, BorderLayout.SOUTH);
        add(west, BorderLayout.NORTH);
        south.add(numberEntry);
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
        //Method Object instantiation
        ComplexNumber firstComplex = new ComplexNumber(); 
        ComplexNumber secondComplex = new ComplexNumber();
        ComplexNumber result = new ComplexNumber();

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


        //Operation
        do
        {
            sign = JOptionPane.showInputDialog(null, "Enter (+-*/) for operation between next complex number");
        }while(!sign.equals("+") && !sign.equals("-") && !sign.equals("*") && !sign.equals("/"));
        
        
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

        while(flag.equalsIgnoreCase("y"))
        {
            //Carrying over previous result to continue by assigning it to firstComplex
            firstComplex.setReal(result.getReal());
            firstComplex.setImaginary(result.getImaginary());

            //Sign between numbers
            do
            {
                sign = JOptionPane.showInputDialog(null, "Enter (+-*/) for operation between next complex number");
            }while(!sign.equals("+") && !sign.equals("-") && !sign.equals("*") && !sign.equals("/"));


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