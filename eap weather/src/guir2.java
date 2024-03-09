import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.google.gson.*;
import okhttp3.*;
import javax.persistence.*;

public class guir2 extends JFrame {

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
    private JTextField cityField; // Changed from JTextField to access its value
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

        mainPanel = new JPanel(new GridLayout(11, 1)); // Updated to accommodate 11 rows

        JLabel titleLabel = new JLabel("Προβολή καιρικών δεδομένων ανά πόλη");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        searchPanel = new JPanel(new FlowLayout());
        addPanel("Πόλη:", searchPanel);

        // Added search button
        searchButton = createSearchButton();
        searchPanel.add(searchButton);

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
        cityField = new JTextField(20);
        cityField.setMargin(new Insets(2, 2, 2, 2));

        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(label);
        panel.add(cityField);
        
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
                System.out.println("Αποθήκευση δεδομένων");
            }
        });
        
    
        button1.setVerticalTextPosition(SwingConstants.CENTER);
        button1.setHorizontalTextPosition(SwingConstants.LEFT);
        button1.setPreferredSize(new Dimension(300, 30));
        return button1;
    }

    private JButton createButton2() {
        JButton button2 = new JButton("Αντικατάσταση δεδομένων");
        button2.setFocusable(false);
        button2.setToolTipText("Αντικατάσταση δεδομένων στη βάση");
        button2.setFont(new Font("Arial", Font.PLAIN, 15));
        button2.setMargin(new Insets(10, 10, 10, 10));
    
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Αντικατάσταση δεδομένων");
            }
        });
        
    
        button2.setVerticalTextPosition(SwingConstants.CENTER);
        button2.setHorizontalTextPosition(SwingConstants.LEFT);
        button2.setPreferredSize(new Dimension(300, 30));
        return button2;
    }

    private JButton createButton3() {
        JButton button3 = new JButton("Διαγραφή δεδομένων");
        button3.setFocusable(false);
        button3.setToolTipText("Διαγραφή δεδομένων από τη βάση");
        button3.setFont(new Font("Arial", Font.PLAIN, 15));
        button3.setMargin(new Insets(10, 10, 10, 10));
    
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Διαγραφή δεδομένων");
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
    
        // Add ActionListener to handle button click
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                fetchWeatherData(city);
            }
        });
    
        // Set button text position
        searchButton.setVerticalTextPosition(SwingConstants.CENTER);
        searchButton.setHorizontalTextPosition(SwingConstants.LEFT);
        searchButton.setPreferredSize(new Dimension(100, 30));
        return searchButton;
    }
    

    private void fetchWeatherData(String city) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://wttr.in/" + city + "?format=j1";
        Request request = new Request.Builder()
            .url(url)
            .build();

        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            parseWeatherData(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseWeatherData(String responseData) {
        System.out.println("Response data: " + responseData);
        Gson gson = new Gson();
        WeatherData weatherData = gson.fromJson(responseData, WeatherData.class);
    
        if (weatherData != null && weatherData.getCurrentObservation() != null) {
            CurrentObservation currentObservation = weatherData.getCurrentObservation();
    
            String temperature = currentObservation.getTemperature().getTemperature();
            String humidity = currentObservation.getAtmosphere().getHumidity();
            String windSpeed = currentObservation.getWind().getSpeed();
            String UVIndex = currentObservation.getUVIndex();
            String weatherDescription = currentObservation.getCondition().getText();
            String date = currentObservation.getObservationTime();
        } else {
            System.out.println("Error: Failed to parse weather data");
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
