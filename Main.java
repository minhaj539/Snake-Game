package My__SnakeProject;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    JFrame frame;
    Main(){
        frame=new JFrame("my frame");
        frame.setBounds(10,10,905,700);
        frame.setResizable(false);
        gamePanel panel=new gamePanel();
        frame.add(panel);
        frame.setBackground(Color.black);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        Main obj=new Main();
    }
}
