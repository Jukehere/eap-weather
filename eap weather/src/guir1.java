import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class guir1 {

	private JFrame frame;
	private JPanel panel;
	private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

	public guir1() {
		window();
	}

	private void window() {
		frame = new JFrame();
		frame.setTitle("Weather Application");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);

		panel = new JPanel();
		
		button1 = createButton1();
		panel.add(button1);

		frame.add(panel, BorderLayout.CENTER);
	}

	private JButton createButton1() {
		JButton button1 = new JButton("Test");
		button1.setFocusable(false);
		button1.setIconTextGap(10);
		button1.setToolTipText("Tooltip");
		button1.setFont(new Font("Arial", Font.PLAIN, 24));
		button1.setMargin(new Insets(10, 10, 10, 10));
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Το κουμπί δουλεύει");
			}
		});
		
		button1.setVerticalTextPosition(SwingConstants.CENTER);
		button1.setHorizontalTextPosition(SwingConstants.LEFT);
		button1.setPreferredSize(new Dimension(200, 35));
		button1.setMnemonic(KeyEvent.VK_P);
		return button1;
	}

	public void show() {
		this.frame.setVisible(true);
	}
}