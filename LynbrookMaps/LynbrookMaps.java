import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

/**
 * @author Paurush Pandey and Joe Lin
 * 
 * Program to give directions to users on the Lynbrook campus
 *
 */
public class LynbrookMaps {
	static Location[][] mapSpaces = new Location[10][20];
	static int[][] walkable = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 },
			{ 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 },
			{ 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1 },
			{ 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1 },
			{ 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int fDigit = 1;
		int sDigit = 1;
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 20; c++) {
				mapSpaces[r][c] = new Location("images/image_" + fDigit + "_" + sDigit + ".jpeg", walkable[r][c] == 0,
						false);
				if (sDigit == 20) {
					sDigit = 0;
					fDigit++;
				}
				sDigit++;
			}
		}

		MapDisplay md = new MapDisplay(mapSpaces);
		md.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		md.setBounds(0, 0, 1500, 750);
		md.setVisible(true);
	}

}



