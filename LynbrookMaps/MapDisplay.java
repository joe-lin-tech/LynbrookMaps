import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MapDisplay extends JFrame {

	private JTextField userLocationX;
	private JTextField userLocationY;
	private JTextField destinationX;
	private JTextField destinationY;
	private JButton directionsButton;
	private static JFrame thisWindow;

	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	public MapDisplay() {
		userLocationX = new JTextField("User X Location");
		userLocationY = new JTextField("User Y Location");
		destinationX = new JTextField("Destination X Location");
		destinationY = new JTextField("Destination Y Location");
		directionsButton = new JButton("Directions");
		thisWindow = this;

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		

		JPanel displayPanel = new JPanel();
		
		
		displayPanel.setLayout(gridbag);
//		displayPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 20.0;

		JLabel userXLabel = new JLabel("User X Location:", JLabel.RIGHT);

//        LoginListener loginListener = new LoginListener();
		JLabel userYLabel = new JLabel("User Y Location:", JLabel.RIGHT);

//        directionsButton.addActionListener( loginListener );

		displayPanel.add(userXLabel);
		gridbag.setConstraints(userLocationX, gbc);
		displayPanel.add(userLocationX);

		displayPanel.add(userYLabel);
		gridbag.setConstraints(userLocationY, gbc);
		displayPanel.add(userLocationY);
		displayPanel.add(directionsButton);

		Container c = getContentPane();
		c.add(displayPanel);
	}

}

//
//    /******************************************************************/
//    /*** passwordField and "Login" button events handling ***/
//    /******************************************************************/
//
//    private class LoginListener implements ActionListener
//    {
//        public void actionPerformed( ActionEvent e )
//        {
//            String name = nameField.getText().trim().toLowerCase();
//            String password = ( new String( passwordField.getPassword() ) )
//                .trim()
//                .toLowerCase();
//            tryLogin( name, password );
//            nameField.setText( "" );
//            passwordField.setText( "" );
//        }
//
//
//        private void tryLogin( String name, String password )
//        {
//            String errorMsg = "";
//            int result = server.login( name, password );
//
//            if ( result < 0 )
//            {
//                if ( result == -1 )
//                    errorMsg = "User unknown";
//                else if ( result == -2 )
//                    errorMsg = "Invalid password";
//                else if ( result == -3 )
//                    errorMsg = "User already logged in";
//                else
//                    errorMsg = "Unknown error code";
//
//                JOptionPane.showMessageDialog( thisWindow,
//                    errorMsg,
//                    "Login failed",
//                    JOptionPane.ERROR_MESSAGE );
//            }
//        }
//    }
//
