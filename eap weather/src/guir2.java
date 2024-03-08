import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class guir2 extends JFrame {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel searchPanel;
    private JPanel extraPanel1;
    private JPanel extraPanel2;
    private JPanel extraPanel3;
    private JPanel extraPanel4;
    private JPanel extraPanel5;
    private JPanel extraPanel6;
    private JPanel extraPanel7;
    private JPanel extraPanel8;
    private JPanel buttonPanel;
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

        extraPanel1 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 1:", extraPanel1);

        extraPanel2 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 2:", extraPanel2);

        extraPanel3 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 3:", extraPanel3);

        extraPanel4 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 4:", extraPanel4);

        extraPanel5 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 5:", extraPanel5);

        extraPanel6 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 6:", extraPanel6);

        extraPanel7 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 7:", extraPanel7);

        extraPanel8 = new JPanel(new FlowLayout());
        addPanel("Πρόσθετη Ετικέτα 8:", extraPanel8);
        
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

        panel.add(label);
        panel.add(textField);
        
        mainPanel.add(panel);
    }

    private JButton createButton1() {
		JButton button1 = new JButton("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFocusable(false);
		button1.setToolTipText("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFont(new Font("Arial", Font.PLAIN, 18));
		button1.setMargin(new Insets(10, 10, 10, 10));
	
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Προβολή καιρικών δεδομένων ανά πόλη");
				JFrame currentFrame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
				currentFrame.dispose();
				guir2 newFrame = new guir2();
				newFrame.setVisible(true);
			}
		});
		
	
		button1.setVerticalTextPosition(SwingConstants.CENTER);
		button1.setHorizontalTextPosition(SwingConstants.LEFT);
		button1.setPreferredSize(new Dimension(475, 35));
		return button1;
	}

    private JButton createButton2() {
		JButton button1 = new JButton("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFocusable(false);
		button1.setToolTipText("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFont(new Font("Arial", Font.PLAIN, 18));
		button1.setMargin(new Insets(10, 10, 10, 10));
	
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Προβολή καιρικών δεδομένων ανά πόλη");
				JFrame currentFrame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
				currentFrame.dispose();
				guir2 newFrame = new guir2();
				newFrame.setVisible(true);
			}
		});
		
	
		button1.setVerticalTextPosition(SwingConstants.CENTER);
		button1.setHorizontalTextPosition(SwingConstants.LEFT);
		button1.setPreferredSize(new Dimension(475, 35));
		return button1;
	}

    private JButton createButton3() {
		JButton button1 = new JButton("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFocusable(false);
		button1.setToolTipText("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFont(new Font("Arial", Font.PLAIN, 18));
		button1.setMargin(new Insets(10, 10, 10, 10));
	
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Προβολή καιρικών δεδομένων ανά πόλη");
				JFrame currentFrame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
				currentFrame.dispose();
				guir2 newFrame = new guir2();
				newFrame.setVisible(true);
			}
		});
		
	
		button1.setVerticalTextPosition(SwingConstants.CENTER);
		button1.setHorizontalTextPosition(SwingConstants.LEFT);
		button1.setPreferredSize(new Dimension(475, 35));
		return button1;
	}

    public void show() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        guir2 myGui = new guir2();
        myGui.show();
    }
}
