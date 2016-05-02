/**
 * Created by Daria on 06.01.2016.
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UIFileCopy {

    static JFrame mainFrame;
    static JPanel panel;
    static String bDay;
    static String cDay;
    static JTextField bDayF;
    static JTextField cDayF;
    static JLabel birthDay;
    static JLabel currentDay;
    static JButton get;
    static JLabel answer;

    public static void main(String[] args) {

        mainFrame = new JFrame("Your biorithms");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setBounds(1000, 500, 1000, 500);

        panel = new JPanel();
        BoxLayout bb = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(bb);
        panel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(Box.createVerticalStrut(20));


        birthDay = new JLabel();
        birthDay.setText("Enter your birthday");
        bDayF = new JTextField();

        currentDay = new JLabel();
        currentDay.setText("Enter cuurent date");
        cDayF = new JTextField();

        answer = new JLabel();

        get = new JButton("Get result");
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bDay = bDayF.getText();
                cDay = cDayF.getText();
                System.err.println(bDay);
                answer.setText("Result!");
            }
        });
        get.setPreferredSize(new Dimension(100, 300));

        panel.add(birthDay);
        panel.add(bDayF);
        panel.add(currentDay);
        panel.add(cDayF);
        panel.add(get);
        panel.add(answer);
        mainFrame.getContentPane().add(panel);
        mainFrame.setVisible(true);

    }


}

