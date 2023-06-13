package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window () {
        super("A little");

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        setBounds(width/2, 0, 600, height);

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame window = new Window();
    }

}
