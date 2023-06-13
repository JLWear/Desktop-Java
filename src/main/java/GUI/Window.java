package GUI;

import javax.swing.*;

public class Window extends JFrame {
    public Window(){
        super("A window");
        setBounds(1000,0, 500, 500);

        // Centre une fenetre
        //setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame window = new Window();
    }
}

