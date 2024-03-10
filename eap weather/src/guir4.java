import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import okhttp3.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sasga
 */
public class guir4 extends JFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    
    public guir4 () {
        window();
    }




    private void window() {
        frame = new JFrame();
        frame.setTitle("Στατιστικά");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 520);
        frame.setLocationRelativeTo(null);
        
        mainPanel = new JPanel(new GridLayout(11, 1));
    
        JLabel titleLabel = new JLabel("Στατιστικά");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        backButton = createBackButton();
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        //buttonPanel.add(button2);
        //buttonPanel.add(button3);
    
        mainPanel.add(buttonPanel);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Πίσω στο μενού");
        backButton.setFocusable(false);
        backButton.setToolTipText("Πίσω στο μενού");
        backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setMargin(new Insets(10, 10, 10, 10));
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                guir1 frame = new guir1();
                frame.show();
            }
        });
        
        backButton.setVerticalTextPosition(SwingConstants.CENTER);
        backButton.setHorizontalTextPosition(SwingConstants.LEFT);
        backButton.setPreferredSize(new Dimension(300, 30));
        return backButton;
    }
    
    public void show() {
        this.frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        guir4 myGui = new guir4();
        myGui.show();
    }
}