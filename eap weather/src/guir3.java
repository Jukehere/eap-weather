import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class guir3 extends JFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    private JList<String> citiesList;

    private final String url = "jdbc:derby://localhost:1527/weatherdata"; // Αντικαταστήστε με το URL της βάσης σας
    private final String user = "weatherdata"; // Ο χρήστης της βάσης δεδομένων σας
    private final String password = "weatherdata"; // Ο κωδικός πρόσβασης

    public guir3() {
        window();
    }

    private void window() {
        frame = new JFrame();
        frame.setTitle("Λίστα πόλεων και ημερομηνιών");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(740, 520);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Λίστα πόλεων και ημερομηνιών");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        List<String> cities = getCitiesFromDatabase();
        citiesList = new JList<>(cities.toArray(new String[0]));
        citiesList.setFont(new Font("Arial", Font.PLAIN, 18));
        citiesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = citiesList.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        String selectedCity = citiesList.getModel().getElementAt(index).toString();
                        displayWeatherDatesForCity(selectedCity);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(citiesList);
        scrollPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()-100));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(scrollPane);

        backButton = createBackButton();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true); // Προσθήκη αυτής της γραμμής για να είναι ορατό το παράθυρο
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
    String query = "SELECT DISTINCT LOWER(areaname) AS areaname FROM City";

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            String areaname = rs.getString("areaname");
            cities.add(areaname.toLowerCase()); 
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return cities;
}

    private void displayWeatherDatesForCity(String selectedCity) {
        List<String> dates = getWeatherDatesFromDatabaseForCity(selectedCity);
        if (dates.isEmpty()) {
            System.out.println("Δεν βρέθηκαν ημερομηνίες για την πόλη: " + selectedCity);
        } else {
            JList<String> datesList = new JList<>(dates.toArray(new String[0]));
            datesList.setFont(new Font("Arial", Font.PLAIN, 18));

            JScrollPane datesScrollPane = new JScrollPane(datesList);
            datesScrollPane.setPreferredSize(new Dimension(340, 100));

            JDialog datesDialog = new JDialog(frame, "Ημερομηνίες αναζήτησης καιρού για την πόλη " + selectedCity, true);
            datesDialog.getContentPane().add(datesScrollPane);
            datesDialog.pack();
            datesDialog.setLocationRelativeTo(frame);
            datesDialog.setVisible(true);
        }
    }

    private List<String> getWeatherDatesFromDatabaseForCity(String selectedCity) {
    List<String> dates = new ArrayList<>();
    String query = "SELECT WEATHER_DATE FROM WEATHERDATA.CITYDATA WHERE LOWER(CITYNAME) = LOWER(?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, selectedCity);
            ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String date = rs.getString("WEATHER_DATE");
            dates.add(date);
        }

    } catch (SQLException ex) {
        System.err.println("Σφάλμα σύνδεσης με τη βάση δεδομένων: " + ex.getMessage());
        ex.printStackTrace();
    }
        return dates;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new guir3();
            }
        });
    }
}