
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*
    ----TODO----
*/

public class Calculate extends JFrame
{
    private JLabel historyLabel;
    private JButton numberEntry;
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
        historyArea.setPreferredSize(new Dimension(200,25));
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

        //Staging Components
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(west, BorderLayout.NORTH);
        add(east, BorderLayout.EAST);
        south.add(numberEntry);
        west.add(historyLabel);
        west.add(historyArea);
    }

    //Handels button action
    private class ButtonPress implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String action = e.getActionCommand();
            if(action.equals("Enter Numbers"))
                numberInput();
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
                //This exits entire method if user clicks cancel button
                if(inputReal == null)
                    return;
                firstComplex.setReal(Integer.parseInt(inputReal));
                repeat = false;
            } catch (NumberFormatException e) {
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
                    //This exits entire method if user clicks cancel button
                    if(inputImaginary == null)
                        return;
                    if(inputImaginary.contains("i"))
                        JOptionPane.showMessageDialog(null, "Don't include i please :(", "Big no no", JOptionPane.ERROR_MESSAGE);
                    repeat = false;
                }while(inputImaginary.contains("i"));
                firstComplex.setImaginary(Integer.parseInt(inputImaginary));
            } catch (NumberFormatException e) {
                repeat = true;
            }
        }

        /*
        Keeping track of entered complex number to generate history list
        Annonymous object because firstComplex changes and just adding itself just adds a
        reference to it's memory so history wouldnt be accurate as it is changing
        */
        complexHistory.add(new ComplexNumber(firstComplex.getReal(), firstComplex.getImaginary()));

        //Operation
        try{
            do
            {
                sign = JOptionPane.showInputDialog(null, "Enter (+-*/) for operation between next complex number\nNote: Division will round to nearest integer");
            }while(!sign.equals("+") && !sign.equals("-") && !sign.equals("*") && !sign.equals("/"));
        } catch(NullPointerException e) {
            return;
        }
        

        //Keeping track of entered sign to generate history list
        signHistory.add(sign);

        //Second Number Real Component Assignment
        repeat = true;
        while(repeat)
        {
            try {
                inputReal = JOptionPane.showInputDialog(null, "Enter real component below: ");
                //This exits entire method if user clicks cancel button
                if(inputReal == null)
                    return;
                secondComplex.setReal(Integer.parseInt(inputReal));
                repeat = false;
            } catch (NumberFormatException e) {
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
                    //This exits entire method if user clicks cancel button or else NullPointerException would occur
                    if(inputImaginary == null)
                        return;
                    if(inputImaginary.contains("i"))
                        JOptionPane.showMessageDialog(null, "Don't include i please :(", "Big no no", JOptionPane.ERROR_MESSAGE);
                    repeat = false;
                }while(inputImaginary.contains("i"));
                secondComplex.setImaginary(Integer.parseInt(inputImaginary));
            } catch (NumberFormatException e) {
                repeat = true;
            }

        }

        //Keeping track of entered complex number to generate history list
        complexHistory.add(new ComplexNumber(secondComplex.getReal(), secondComplex.getImaginary()));


        //Calling method based on inputted operation
        if(sign.equals("+"))
            result = ComplexNumber.complexAddition(firstComplex, secondComplex);
        else if(sign.equals("-"))
            result = ComplexNumber.complexSubtraction(firstComplex, secondComplex);
        else if(sign.equals("*"))
            result = ComplexNumber.complexMultiplication(firstComplex, secondComplex);
        else if(sign.equals("/"))
            result = ComplexNumber.complexDivision(firstComplex, secondComplex);

        //If statement here just keeps the imaginary sign, prevents the program from saying +-
        if(String.valueOf(result.getImaginary()).charAt(0) == '-')
            flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + result.getImaginary() + "i" + "\nType 'Y' if you wish to proceed");
        else
            flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + "+" + result.getImaginary() + "i" + "\nType 'Y' if you wish to proceed");

        if(flag == null)
            flag = " ";

        //This section executes as long as user wishes to continue
        while(flag.equalsIgnoreCase("y"))
        {
            //Carrying over previous result because this is continuing from there using this as first term
            firstComplex.setReal(result.getReal());
            firstComplex.setImaginary(result.getImaginary());

            //Sign between numbers
            do
            {
                sign = JOptionPane.showInputDialog(null, "Enter (+-*/) for operation between next complex number\nNote: Division will round to nearest integer");
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
                } catch (NumberFormatException e) {
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
                } catch (NumberFormatException e) {
                    repeat = true;
                }
            }

            //Adding to complexNumber history
            complexHistory.add(new ComplexNumber(secondComplex.getReal(), secondComplex.getImaginary()));

            //Determines which computational method to call based on operation chosen by user
            if(sign.equals("+"))
                result = ComplexNumber.complexAddition(firstComplex, secondComplex);
            else if(sign.equals("-"))
                result = ComplexNumber.complexSubtraction(firstComplex, secondComplex);
            else if(sign.equals("*"))
                result = ComplexNumber.complexMultiplication(firstComplex, secondComplex);

            //If statement here just keeps the imaginary sign, prevents the program from saying +-
            if(String.valueOf(result.getImaginary()).charAt(0) == '-')
                flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + result.getImaginary() + "i" + "\nType 'Y' if you wish to proceed");
            else
                flag = JOptionPane.showInputDialog(null, "Your Result is: " + result.getReal() + "+" + result.getImaginary() + "i" + "\nType 'Y' if you wish to proceed");
        
            if(flag == null)
                flag = " ";
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

}
