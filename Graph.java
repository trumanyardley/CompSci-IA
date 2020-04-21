
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;

public class Graph extends JFrame
{

    private Button addComplexNumbers, clear, graph;
    private JPanel buttons;
    private GraphPanel gPanel;

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
        clear = new Button("Clear Numbers");
        graph = new Button("Graph");

        //Panels
        gPanel = new GraphPanel();
        buttons = new JPanel();
        buttons.setSize(400, 100);
        buttons.setLayout(new FlowLayout());
        buttons.add(addComplexNumbers);
        buttons.add(clear);
        buttons.add(graph);

        //Scene Setup
        add(gPanel);
        add(buttons, BorderLayout.SOUTH);
    }

    

}