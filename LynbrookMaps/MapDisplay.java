import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class MapDisplay extends JFrame implements ActionListener
{

    private static JFrame thisWindow;

    private int[] setLocationState;

    private int[] setDestinationState;

    private static JButton[][] buttons = new JButton[10][20];

    private int[] currentButton;

    private static JFrame frame;

    private static JFrame dFrame;

    private JLabel locationLabel;

    private JLabel destinationLabel;

    private Location[][] mapSpaces;


    public MapDisplay( Location[][] mapSpaces ) throws IOException
    {
        this.mapSpaces = mapSpaces;
        thisWindow = this;
        setLocationState = new int[] { -1, -1 };
        setDestinationState = new int[] { -1, -1 };
        currentButton = null;

        GridLayout gridLayout = new GridLayout( 10, 20, 0, 0 );

        JPanel displayPanel = new JPanel( gridLayout );

        Container c = getContentPane();

        JButton button;

        for ( int row = 0; row < 10; row++ )
        {
            for ( int col = 0; col < 20; col++ )
            {
//				System.out.println(mapSpaces[row][col].getImagePath());
                button = new JButton();
                button.setIcon(
                    new ImageIcon( mapSpaces[row][col].getImagePath() ) );
//				button.setBorderPainted(false);
//				button.setBorder(new LineBorder(Color.BLACK));
                button.setActionCommand( "SubWindow" );
                button.addActionListener( this );
                displayPanel.add( button );
                buttons[row][col] = button;
            }
        }

        c.add( displayPanel );
        dFrame = new JFrame();
        GridLayout gb = new GridLayout( 3, 1, 0, 0 );
        JPanel panel = new JPanel( gb );

        locationLabel = new JLabel( "Location Coords: " + setLocationState[0]
            + ", " + setLocationState[1] );
        destinationLabel = new JLabel( "Destination Coords: "
            + setDestinationState[0] + ", " + setDestinationState[1] );

        JButton directionsButton = new JButton( "Get Directions" );
        directionsButton.setActionCommand( "Directions" );
        directionsButton.addActionListener( this );

        panel.add( locationLabel );
        panel.add( destinationLabel );
        panel.add( directionsButton );

        dFrame.add( panel );
        dFrame.setSize( new Dimension( 500, 300 ) );
        dFrame.setVisible( true );

    }


    @Override
    public void actionPerformed( ActionEvent e )
    {
        // TODO Auto-generated method stub

        Location imMad = mapSpaces[8][10];
        System.out.println( getX( imMad ) + " " + getY( imMad ) );

        String cmd = e.getActionCommand();
        if ( cmd.equals( "SubWindow" ) )
        {
            for ( int row = 0; row < 10; row++ )
            {
                for ( int col = 0; col < 20; col++ )
                {
                    if ( e.getSource().equals( buttons[row][col] ) )
                    {
                        currentButton = new int[] { row, col };
                        System.out.println(
                            currentButton[0] + ", " + currentButton[1] );
                    }
                }
            }
            frame = new JFrame();
            GridBagLayout gb = new GridBagLayout();
            GridBagConstraints gbc = new GridBagConstraints();
            JPanel panel = new JPanel( gb );
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton locationButton = new JButton(
                "Set This as Your Location" );
            locationButton.setActionCommand( "Location" );
            locationButton.addActionListener( this );
            JButton destinationButton = new JButton(
                "Set This as Your Destination" );
            destinationButton.setActionCommand( "Destination" );
            destinationButton.addActionListener( this );
            panel.add( locationButton, gbc );

            panel.add( destinationButton, gbc );

            // panel.add(userLocationX, gbc);
            //
            // panel.add(userLocationY, gbc);
            frame.add( panel );
            frame.setSize( new Dimension( 750, 200 ) );
            frame.setVisible( true );
            System.out.println( "works" );
        }
        else if ( cmd.equals( "Location" ) )
        {
            setLocationState = currentButton;
            System.out.println( currentButton );
            locationLabel.setText( "Location Coords: " + setLocationState[0]
                + ", " + setLocationState[1] );
            frame.setVisible( false );
        }
        else if ( cmd.equals( "Destination" ) )
        {
            setDestinationState = currentButton;
            destinationLabel.setText( "Destination Coords: "
                + setDestinationState[0] + ", " + setDestinationState[1] );
            frame.setVisible( false );
        }
        else if ( cmd.equals( "Directions" ) )
        {
            if ( setLocationState[0] != -1 && setDestinationState[0] != -1 )
            {
                ArrayList<Location> path = pathFinder( setLocationState[0],
                    setLocationState[1],
                    setDestinationState[0],
                    setDestinationState[1] );
                for ( Location loc : path )
                {
                    buttons[getX( loc )][getY( loc )].setText( "PATH" );
                    buttons[getX( loc )][getY( loc )]
                        .setHorizontalTextPosition( SwingConstants.CENTER );
                    System.out.println( getX( loc ) + ", " + getY( loc ) );
                }
                System.out.println( path );
                frame.setVisible( false );
            }
        }
    }


    public ArrayList<Location> pathFinder( int l1x, int l1y, int l2x, int l2y )
    {

        Location[][] previous = new Location[10][20];
        ArrayList<Location> checked = new ArrayList<Location>();
        Queue<Location> list = new LinkedList<Location>();

        list.add( mapSpaces[l1x][l1y] );
        while ( !list.isEmpty() )
        {
            Location l = list.remove();
            checked.add( l );
            int x = getX( l );
            int y = getY( l );
            // System.out.println(x + ", " + y);
            if ( x == -1 || y == -1 )
            {
                // System.out.println(l);
                // System.out.println();
            }

            if ( l.equals( mapSpaces[l2x][l2y] ) )
            {
                break;
            }
            if ( l.isWalkable() )
            {
                if ( x + 1 < 10 && !checked.contains( mapSpaces[x + 1][y] ) )
                {
                    list.add( mapSpaces[x + 1][y] );
                    previous[x + 1][y] = l;

                }
                if ( y + 1 < 20 && !checked.contains( mapSpaces[x][y + 1] ) )
                {
                    list.add( mapSpaces[x][y + 1] );
                    previous[x][y + 1] = l;

                }
                if ( x - 1 >= 0 && !checked.contains( mapSpaces[x - 1][y] ) )
                {
                    list.add( mapSpaces[x - 1][y] );
                    previous[x - 1][y] = l;

                }
                if ( y - 1 >= 0 && !checked.contains( mapSpaces[x][y - 1] ) )
                {
                    list.add( mapSpaces[x][y - 1] );
                    previous[x][y - 1] = l;

                }

            }

        }
        ArrayList<Location> path = new ArrayList<Location>();
        Location l = mapSpaces[l2x][l2y];
        while ( l != null )
        {
            path.add( l );
            l = previous[getX( l )][getY( l )];
        }
        return path;

    }


    public int getX( Location l )
    {
        for ( int i = 0; i < mapSpaces.length; i++ )
        {
            for ( int j = 0; j < mapSpaces[0].length; j++ )
            {
                if ( mapSpaces[i][j].equals( l ) )
                {
                    return i;
                }
            }
        }
        return -1;
    }


    public int getY( Location l )
    {
        for ( int i = 0; i < mapSpaces.length; i++ )
        {
            for ( int j = 0; j < mapSpaces[0].length; j++ )
            {
                if ( mapSpaces[i][j].equals( l ) )
                {
                    return j;
                }
            }
        }
        return -1;
    }

}
