import java.awt.*;
import javax.swing.*;

public class guir2 extends JFrame {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel searchPanel;

    public guir2() {
        window();
    }

    private void window() {
        frame = new JFrame();
        frame.setTitle("Προβολή καιρικών δεδομένων ανά πόλη");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 520);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new GridLayout(3, 1));

        JLabel titleLabel = new JLabel("Προβολή καιρικών δεδομένων ανά πόλη");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        searchPanel = new JPanel(new FlowLayout());

        JTextField searchBox = new JTextField(20);
        searchBox.setMargin(new Insets(2, 2, 2, 2));

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String searchText = searchBox.getText();
            System.out.println("Searching for: " + searchText);
        });

        searchPanel.add(searchBox);
        searchPanel.add(searchButton);

        mainPanel.add(searchPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        guir2 myGui = new guir2();
        myGui.show();
    }
}
