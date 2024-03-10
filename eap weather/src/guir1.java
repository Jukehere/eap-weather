import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class guir1 extends JFrame {

	private JFrame frame;
	private JPanel panel;
	private JButton button1;
    private JButton button2;
    private JButton button3;
	private JButton exitbutton;

	public guir1() {
		window();
	}

	private void window() {
		frame = new JFrame();
		frame.setTitle("Weather Application");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1200, 520);
		frame.setLocationRelativeTo(null);
	
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		button1 = createButton1();
		button2 = createButton2();
		button3 = createButton3();
		exitbutton = createExitButton();

		JLabel titleLabel = new JLabel("Weather Application");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(titleLabel);
        ImageIcon imageIcon = new ImageIcon("eap weather\\src\\weather.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel subLabel = new JLabel("Σοφιανόπουλος - Τζάνης");
		subLabel.setFont(new Font("Arial", Font.BOLD, 30));
		subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(titleLabel);
        panel.add(imageLabel);
		panel.add(subLabel);
		JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(exitbutton);

        panel.add(buttonPanel);
	
		frame.add(panel, BorderLayout.CENTER);
	}
	

	private JButton createButton1() {
		JButton button1 = new JButton("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFocusable(false);
		button1.setToolTipText("Προβολή καιρικών δεδομένων ανά πόλη");
		button1.setFont(new Font("Arial", Font.PLAIN, 24));
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
		JButton button2 = new JButton("Προβολή λίστας πόλεων και ημερομηνιών");
		button2.setFocusable(false);
		button2.setToolTipText("Προβολή λίστας πόλεων και ημερομηνιών");
		button2.setFont(new Font("Arial", Font.PLAIN, 24));
		button2.setMargin(new Insets(10, 10, 10, 10));
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Προβολή λίστας πόλεων και ημερομηνιών");
                                JFrame currentFrame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
				currentFrame.dispose();
				guir3 newFrame = new guir3();
				newFrame.setVisible(true);
			}
		});
		
		button2.setVerticalTextPosition(SwingConstants.CENTER);
		button2.setHorizontalTextPosition(SwingConstants.LEFT);
		button2.setPreferredSize(new Dimension(550, 35));
		return button2;
	}

	private JButton createButton3() {
		JButton button3 = new JButton("Προβολή στατιστικών ανά πόλη");
		button3.setFocusable(false);
		button3.setToolTipText("Δείτε τα στατιστικά ανά πόλη, και εκτυπώστε σε PDF");
		button3.setFont(new Font("Arial", Font.PLAIN, 24));
		button3.setMargin(new Insets(10, 10, 10, 10));
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Προβολή στατιστικών ανά πόλη");
                                JFrame currentFrame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
				currentFrame.dispose();
				guir4 newFrame = new guir4();
				newFrame.setVisible(true);
			}
		});
		
		button3.setVerticalTextPosition(SwingConstants.CENTER);
		button3.setHorizontalTextPosition(SwingConstants.LEFT);
		button3.setPreferredSize(new Dimension(400, 35));
		return button3;
	}


	private JButton createExitButton() {
		JButton exitButton = new JButton("Exit");
		exitButton.setFocusable(false);
		exitButton.setToolTipText("Exit the program");
		exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
		exitButton.setMargin(new Insets(10, 10, 10, 10));
	
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	
		exitButton.setVerticalTextPosition(SwingConstants.CENTER);
		exitButton.setHorizontalTextPosition(SwingConstants.LEFT);
		exitButton.setPreferredSize(new Dimension(200, 35));
		return exitButton;
	}
	

	public void show() {
		this.frame.setVisible(true);
	}
}