
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{

    private static Calculate calculateWindow = new Calculate();
    private static Graph graphWindow = new Graph();

    private static final int HEIGHT = 400, WIDTH = 400;
    private final JPanel south;
    private final JButton calculate, graph;
    private final JLabel heading;

    public MainWindow()
    {
        //Setting up window attributes
        super("Complex Number Calculator");
        setSize(HEIGHT, WIDTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        //Look and feel based on platform
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

        //Action Listener
        calculate.addActionListener(new OpenNextWindow());
        graph.addActionListener(new OpenNextWindow());

        //TextArea                                  HTML to wrap text
        heading = new JLabel("<html><p>Press calculate to perform complex calculatios and graph to graph complex points.</p></html>");

        //Staging the Components
        add(south, BorderLayout.SOUTH);
        add(heading, BorderLayout.CENTER);
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