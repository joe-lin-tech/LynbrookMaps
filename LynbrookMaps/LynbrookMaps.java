import javax.swing.JFrame;
public class LynbrookMaps
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Location[][] mapSpaces = new Location[10][10];
		MapDisplay md = new MapDisplay();
		md.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        md.setBounds( 0, 0, 1000, 1000 );
        md.setVisible( true );
	}

}
