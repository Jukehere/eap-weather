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

public class guir2 extends JFrame {

    private static final String DB_URL = "jdbc:derby://localhost:1527/weatherdata";
    private static final String USER = "weatherdata";
    private static final String PASS = "weatherdata";

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel searchPanel;
    private JButton searchButton;
    private JPanel extraPanel1;
    private JPanel extraPanel2;
    private JPanel extraPanel3;
    private JPanel extraPanel4;
    private JPanel extraPanel5;
    private JPanel extraPanel6;
    private JPanel extraPanel7;
    private JTextField cityField;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    
    public guir2() {
        window();
    }

    private void window() {
        frame = new JFrame();
        frame.setTitle("Προβολή καιρικών δεδομένων ανά πόλη");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 520);
        frame.setLocationRelativeTo(null);
    
        mainPanel = new JPanel(new GridLayout(11, 1));
    
        JLabel titleLabel = new JLabel("Προβολή καιρικών δεδομένων ανά πόλη");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);
    
        searchPanel = new JPanel(new FlowLayout());
    
        cityField = new JTextField(20);
        cityField.setMargin(new Insets(2, 2, 2, 2));
        JLabel cityLabel = new JLabel("Πόλη:");
        cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(cityLabel);
        searchPanel.add(cityField);
    
        searchButton = createSearchButton();
        searchPanel.add(searchButton);
    
        mainPanel.add(searchPanel);
    
        extraPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addPanel("Θερμοκρασία:", extraPanel1);
    
        extraPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addPanel("Υγρασία:", extraPanel2);
    
        extraPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addPanel("Ταχύτητα ανέμου:", extraPanel3);
    
        extraPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addPanel("UV Index:", extraPanel4);
    
        extraPanel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addPanel("Weather Description:", extraPanel5);
    
        extraPanel6 = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        addPanel("Date:", extraPanel6);
    
        extraPanel7 = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        addPanel("Προβολές:", extraPanel7);
    
        button1 = createButton1();
        button2 = createButton2();
        button3 = createButton3();
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
    
        mainPanel.add(buttonPanel);
    
        frame.add(mainPanel, BorderLayout.CENTER);
    }
    

    private void addPanel(String labelText, JPanel panel) {
        JTextField textField = new JTextField(20);
        textField.setMargin(new Insets(2, 2, 2, 2));
    
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
    
        panel.add(label);
        panel.add(textField);
        
        mainPanel.add(panel);
    }
    

    private JButton createButton1() {
        JButton button1 = new JButton("Αποθήκευση δεδομένων");
        button1.setFocusable(false);
        button1.setToolTipText("Αποθηκεύει τα δεδομένα στη βάση δεδομένων");
        button1.setFont(new Font("Arial", Font.PLAIN, 15));
        button1.setMargin(new Insets(10, 10, 10, 10));
    
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToDatabase();
            }
        });
    
        button1.setVerticalTextPosition(SwingConstants.CENTER);
        button1.setHorizontalTextPosition(SwingConstants.LEFT);
        button1.setPreferredSize(new Dimension(300, 30));
        return button1;
    }

    private JButton createButton2() {
        JButton button2 = new JButton("Διαγραφή δεδομένων");
        button2.setFocusable(false);
        button2.setToolTipText("Διαγραφή δεδομένων από τη βάση");
        button2.setFont(new Font("Arial", Font.PLAIN, 15));
        button2.setMargin(new Insets(10, 10, 10, 10));
    
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDataFromDatabase();
            }
        });
    
        button2.setVerticalTextPosition(SwingConstants.CENTER);
        button2.setHorizontalTextPosition(SwingConstants.LEFT);
        button2.setPreferredSize(new Dimension(300, 30));
        return button2;
    }
    
    private JButton createButton3() {
        JButton button3 = new JButton("Πίσω στο μενού");
        button3.setFocusable(false);
        button3.setToolTipText("Πίσω στο μενού");
        button3.setFont(new Font("Arial", Font.PLAIN, 15));
        button3.setMargin(new Insets(10, 10, 10, 10));
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                guir1 frame = new guir1();
                frame.show();
            }
        });
        
        button3.setVerticalTextPosition(SwingConstants.CENTER);
        button3.setHorizontalTextPosition(SwingConstants.LEFT);
        button3.setPreferredSize(new Dimension(300, 30));
        return button3;
    }
    

    private JButton createSearchButton() {
        JButton searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.setToolTipText("Αναζήτηση στοιχείων με βάση την πρόσφατη ημερομηνία");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.setMargin(new Insets(10, 10, 10, 10));
    
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                fetchWeatherData(city);
            }
        });
    
        searchButton.setVerticalTextPosition(SwingConstants.CENTER);
        searchButton.setHorizontalTextPosition(SwingConstants.LEFT);
        searchButton.setPreferredSize(new Dimension(100, 30));
        return searchButton;
    }
    

    private void fetchWeatherData(String city) {
        System.out.println("Fetching weather data for city: " + city);
    
        OkHttpClient client = new OkHttpClient();
        String apiUrl = "https://wttr.in/" + city + "?format=%t,%h,%w,%P,%C,%l";
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
    
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
    
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    parseWeatherData(responseData);
                } else {
                    System.out.println("Error: Δεν είναι σωστή η πόλη ή υπάρχει πρόβλημα στο API.");
                }
            }
        });
        if (!cityExists(city)) {
            insertCity(city);
        }
        updateViewCount(city);
    
        int views = getViewCount(city);
        JTextField viewsField = (JTextField) ((JPanel) mainPanel.getComponent(8)).getComponent(1);
        viewsField.setText(String.valueOf(views));
    }    
    
    private void parseWeatherData(String responseData) {

        String[] parts = responseData.split(",");

        if (parts.length >= 6) {
            String temperature = parts[0].trim();
            String humidity = parts[1].trim();
            String windSpeed = parts[2].trim();
            String UVIndex = parts[3].trim();
            String weatherDescription = parts[4].trim();
    
            JTextField temperatureField = (JTextField) ((JPanel) mainPanel.getComponent(2)).getComponent(1);
            temperatureField.setText(temperature);
    
            JTextField humidityField = (JTextField) ((JPanel) mainPanel.getComponent(3)).getComponent(1);
            humidityField.setText(humidity);
    
            JTextField windSpeedField = (JTextField) ((JPanel) mainPanel.getComponent(4)).getComponent(1);
            windSpeedField.setText(windSpeed);
    
            JTextField UVIndexField = (JTextField) ((JPanel) mainPanel.getComponent(5)).getComponent(1);
            UVIndexField.setText(UVIndex);
    
            JTextField weatherDescriptionField = (JTextField) ((JPanel) mainPanel.getComponent(6)).getComponent(1);
            weatherDescriptionField.setText(weatherDescription);
    
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = dateFormat.format(calendar.getTime());

            JTextField dateField = (JTextField) ((JPanel) mainPanel.getComponent(7)).getComponent(1);
            dateField.setText(currentDate);
            System.out.println("Temperature: " + temperature);
            System.out.println("Humidity: " + humidity);
            System.out.println("Wind Speed: " + windSpeed);
            System.out.println("UV Index: " + UVIndex);
            System.out.println("Weather Description: " + weatherDescription);
        } else {
            JOptionPane.showMessageDialog(this.frame, "Error: Δεν υπάρχουν επαρκή στοιχεία καιρού.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean cityExists(String city) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM city WHERE areaname = ?")) {
            stmt.setString(1, city);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void insertCity(String city) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO city (areaname, views) VALUES (?, 0)")) {
            stmt.setString(1, city);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    private void updateViewCount(String city) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE city SET views = views + 1 WHERE areaname = ?")) {
            stmt.setString(1, city);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getViewCount(String city) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT views FROM city WHERE areaname = ?")) {
            stmt.setString(1, city);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("views");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private void saveDataToDatabase() {
        String cityName = cityField.getText();
        String temperature = ((JTextField) ((JPanel) mainPanel.getComponent(2)).getComponent(1)).getText();
        String humidity = ((JTextField) ((JPanel) mainPanel.getComponent(3)).getComponent(1)).getText();
        String windSpeed = ((JTextField) ((JPanel) mainPanel.getComponent(4)).getComponent(1)).getText();
        String UVIndex = ((JTextField) ((JPanel) mainPanel.getComponent(5)).getComponent(1)).getText();
        String weatherDescription = ((JTextField) ((JPanel) mainPanel.getComponent(6)).getComponent(1)).getText();
        String date = ((JTextField) ((JPanel) mainPanel.getComponent(7)).getComponent(1)).getText();
    
        if (entryExists(cityName, date)) {
            JOptionPane.showMessageDialog(frame, "Υπάρχει ήδη αυτή η καταχώρηση", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO CITYDATA (CITYNAME, WEATHER_DATE, C_TEMP, HUMIDITY, WSPEEDINKMPH, UVINDEX, WEATHERDESC) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, cityName);
            stmt.setString(2, date);
            stmt.setString(3, temperature);
            stmt.setString(4, humidity);
            stmt.setString(5, windSpeed);
            stmt.setString(6, UVIndex);
            stmt.setString(7, weatherDescription);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Τα δεδομένα αποθηκεύτηκαν με επιτυχία.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Αποτυχία αποθήκευσης δεδομένων.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Αποτυχία αποθήκευσης δεδομένων λόγο προβλήματος στην βάση.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean entryExists(String cityName, String date) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM CITYDATA WHERE CITYNAME = ? AND WEATHER_DATE = ?")) {
            stmt.setString(1, cityName);
            stmt.setString(2, date);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void deleteDataFromDatabase() {
        String cityName = cityField.getText();
        String temperature = ((JTextField) ((JPanel) mainPanel.getComponent(2)).getComponent(1)).getText();
        String humidity = ((JTextField) ((JPanel) mainPanel.getComponent(3)).getComponent(1)).getText();
        String windSpeed = ((JTextField) ((JPanel) mainPanel.getComponent(4)).getComponent(1)).getText();
        String UVIndex = ((JTextField) ((JPanel) mainPanel.getComponent(5)).getComponent(1)).getText();
        String weatherDescription = ((JTextField) ((JPanel) mainPanel.getComponent(6)).getComponent(1)).getText();
        String date = ((JTextField) ((JPanel) mainPanel.getComponent(7)).getComponent(1)).getText();
    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM CITYDATA WHERE CITYNAME = ? AND WEATHER_DATE = ? AND C_TEMP = ? AND HUMIDITY = ? AND WSPEEDINKMPH = ? AND UVINDEX = ? AND WEATHERDESC = ?")) {
            stmt.setString(1, cityName);
            stmt.setString(2, date);
            stmt.setString(3, temperature);
            stmt.setString(4, humidity);
            stmt.setString(5, windSpeed);
            stmt.setString(6, UVIndex);
            stmt.setString(7, weatherDescription);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Επιτυχής διαγραφή.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Δεν υπάρχουν παρόμοια δεδομένα για να διαγραφούν.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Η διαγραφή δεν ήταν επιτυχής.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
           
    public void show() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        guir2 myGui = new guir2();
        myGui.show();
    }
}
