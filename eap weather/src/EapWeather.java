/**
 *
 * @author softa
 */

import java.awt.EventQueue;

public class EapWeather {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				guir1 frame = new guir1();
				frame.show();
			}
		});
	}
}
