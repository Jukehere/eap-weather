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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sasga
 */
public class guir3test extends JFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    
    
    public guir3test() {
        window();
    }




    private void window() {
        frame = new JFrame();
        frame.setTitle("Λίστα πόλεων και ημερομηνιών");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 520);
        frame.setLocationRelativeTo(null);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
        JLabel titleLabel = new JLabel("Λίστα πόλεων και ημερομηνιών");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);
        
        List<String> cities = getCitiesFromDatabase();
        JList<String> citiesList = new JList<>(cities.toArray(new String[0]));
        citiesList.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(citiesList);
        mainPanel.add(scrollPane);
        
        
        backButton = createBackButton();
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
           
        mainPanel.add(buttonPanel);
        
        frame.add(mainPanel, BorderLayout.CENTER);
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

    private List<String> getCitiesFromDatabase() {
    List<String> cities = new ArrayList<>();
    String url = "jdbc:derby://localhost:1527/weatherdata"; // Αντικαταστήστε με το URL της βάσης σας
    String user = "weatherdata"; // Ο χρήστης της βάσης δεδομένων σας
    String password = "weatherdata"; // Ο κωδικός πρόσβασης

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement("SELECT areaname FROM City");
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            cities.add(rs.getString("areaname"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return cities;
}
    
    public void show() {
        this.frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        guir3 myGui = new guir3();
        myGui.show();
    }
}