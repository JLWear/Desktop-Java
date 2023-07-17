package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {

    JMenuItem Registration, ListActivity, Weekly, Exit;

    public Gui () {
        super("SportApp");
        setMenu(); //create menu
        setSize(300, 600);// size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close running program if window are closed
        setLocationRelativeTo(null); // set window position at center
        show();
    }

    private void setMenu() {
        JMenuBar barObj = new JMenuBar(); // create menuBar obj
        JMenu messagesObj = new JMenu("Menu"); //create menu bar menu object

        barObj.setBackground(Color.LIGHT_GRAY); // set menu bar bg color

        Registration = new JMenuItem("Registration");
        Registration.setToolTipText("Push to register"); // write text when u hang mouse over
        Registration.addActionListener(this);
        Registration.setBackground(Color.WHITE); // set menu bar menu options bg color
        messagesObj.add(Registration); // add Registration into messages

        ListActivity = new JMenuItem("List activites");
        ListActivity.setToolTipText("Show all registered activities");
        ListActivity.addActionListener(this);
        ListActivity.setBackground(Color.WHITE);
        messagesObj.add(ListActivity);

        Weekly = new JMenuItem("Weekly Training Load");
        Weekly.setToolTipText("Show weekly training load data");
        Weekly.addActionListener(this);
        Weekly.setBackground(Color.WHITE);
        messagesObj.add(Weekly);

        Exit = new JMenuItem("Exit");
        Exit.setToolTipText("Here you will exit");
        Exit.addActionListener(this);
        Exit.setBackground(Color.WHITE);
        messagesObj.add(Exit);


        barObj.add(messagesObj);
        setJMenuBar(barObj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Container container = this.getContentPane();
        container.removeAll();

        if (e.getSource() == Registration) {
            container.removeAll();
            new GUI.Activities.Register().register(container);
            container.setLayout(new FlowLayout());
        } else if (e.getSource() == ListActivity) {
            container.removeAll();
            new GUI.Activities.DisplayAll().displayAll(container);
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        } else if (e.getSource() == Weekly) {
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            new GUI.Training.Weekly().weekly(container);
        } else if (e.getSource() == Exit){
            int exitReply = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if(exitReply == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        container.repaint();
        container.revalidate();
    }
}
