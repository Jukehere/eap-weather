import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author sasga
 */
public class guir4 extends JFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    
    private final String url = "jdbc:derby://localhost:1527/weatherdata"; // Αντικαταστήστε με το URL της βάσης σας
    private final String user = "weatherdata"; // Ο χρήστης της βάσης δεδομένων σας
    private final String password = "weatherdata"; // Ο κωδικός πρόσβασης
    
    public guir4 () {
        window();
    }


    private void window() {
        frame = new JFrame();
        frame.setTitle("Στατιστικά");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 520);
        frame.setLocationRelativeTo(null);
        
        mainPanel = new JPanel(new BorderLayout());
    
        JLabel titleLabel = new JLabel("Στατιστικά αναζητήσεων ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        
        backButton = createBackButton();
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        Map<String, Integer> searchStats = getSearchStats();
        displaySearchStats(searchStats);
        frame.pack();
        frame.setVisible(true);
        
        
        
        
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
    
    private Map<String, Integer> getSearchStats() {
    Map<String, Integer> citySearchCounts = new LinkedHashMap<>();
    String query = "SELECT UPPER(AREANAME) AS AREANAME, SUM(VIEWS) AS VIEWS FROM WEATHERDATA.CITY GROUP BY UPPER(AREANAME) ORDER BY VIEWS DESC";

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            String cityName = rs.getString("AREANAME").toLowerCase();
            int views = rs.getInt("VIEWS");
            citySearchCounts.put(cityName, views);
        }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return citySearchCounts;
    }
    
    private void displaySearchStats(Map<String, Integer> stats) {
    DefaultTableModel model = new DefaultTableModel(new Object[]{"Πόλη", "Αριθμός Αναζητήσεων"}, 0);
    for (Map.Entry<String, Integer> entry : stats.entrySet()) {
        model.addRow(new Object[]{entry.getKey(), entry.getValue()});
    }
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(600, frame.getHeight()-100));
    mainPanel.add(scrollPane, BorderLayout.CENTER);
    frame.validate();
    frame.repaint();
    
    createTextFile(stats);
    }   
    
    private void createTextFile(Map<String, Integer> stats) {
        try (FileWriter writer = new FileWriter("statistika.txt")) {
            writer.write("Στατιστικά Αναζητήσεων\n");
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   public void showStatsWindow() {
       Map<String, Integer> searchStats= getSearchStats();
       displaySearchStats(searchStats);
       
       frame.pack();
       frame.setVisible(true);
   }
    
    public void show() {
        this.frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        guir4 myGui = new guir4();
        myGui.show();
    }
}