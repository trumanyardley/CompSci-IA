
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{

    private static Calculate calculateWindow = new Calculate();
    private static Graph graphWindow = new Graph();

    private static final int HEIGHT = 400, WIDTH = 400;
    private JPanel south;
    private JButton calculate, graph;
    private JLabel heading, image;

    public MainWindow()
    {
        //Setting up window attributes
        super("Complex Number Calculator");
        setSize(HEIGHT, WIDTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        //Look and feel based on operating system
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception e) {
            e.printStackTrace();
        }

        //Panels
        south = new JPanel(new FlowLayout());

        //Buttons
        calculate = new JButton("Calculate");
        graph = new JButton("Graph");
        calculate.addActionListener(new OpenNextWindow());
        graph.addActionListener(new OpenNextWindow());

        //Labels
        image = new JLabel(new ImageIcon("complexnumber.png"));
        heading = new JLabel("<html><h1>Complex Number Calculator</h1><p><center>by Truman Yardley</center><p></html>");
        heading.setHorizontalAlignment(JLabel.CENTER);

        //Staging the Components
        add(south, BorderLayout.SOUTH);
        add(heading, BorderLayout.NORTH);
        add(image, BorderLayout.CENTER);
        south.add(calculate);
        south.add(graph);
        

    }

    //ActionListener
    private class OpenNextWindow implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e)
        {
            String action = e.getActionCommand();
            if(action.equals("Calculate"))
                calculateWindow.setVisible(true);
            else if(action.equals("Graph"))
                graphWindow.setVisible(true);
        }
    }
    

}