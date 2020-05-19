import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;


public class LynbrookMaps
{
    Location[][] mapSpaces = new Location[20][10];


    public static void main( String[] args )
    {
        // TODO Auto-generated method stub

        MapDisplay md = new MapDisplay();
        md.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        md.setBounds( 0, 0, 1000, 1000 );
        md.setVisible( true );
    }


    public ArrayList<Location> pathFinder( Location l1, Location l2 )
    {
        int l1x = getX(l1);
        int l1y = getY(l1);
        int l2x = getX(l2);
        int l2y = getY(l2);

        
        Location[][] previous = new Location[20][10];
        ArrayList<Location> checked = new ArrayList<Location>();
        Queue<Location> list = new LinkedList<Location>();
        
        list.add( mapSpaces[l1x][l1y] );
        while ( !list.isEmpty() )
        {
            Location l = list.remove();
            checked.add( l );
            int x = getX( l );
            int y = getY( l );
            if ( l.equals( mapSpaces[l2x][l2y] ) )
            {
                break;
            }
            if ( l.isWalkable() )
            {
                if ( x + 1 < mapSpaces.length && !checked.contains( mapSpaces[x+1][y] ))
                {
                    list.add( mapSpaces[x + 1][y] );
                    previous[x+1][y] = l;
                }
                if ( y + 1 < mapSpaces[0].length && !checked.contains( mapSpaces[x][y+1] ))
                {
                    list.add( mapSpaces[x][y + 1] );
                    previous[x][y+1] = l;
                }
                if ( x - 1 >= 0 && !checked.contains( mapSpaces[x-1][y] ))
                {
                    list.add( mapSpaces[x - 1][y] );
                    previous[x-1][y] = l;
                }
                if ( y - 1 >= 0 && !checked.contains( mapSpaces[x][y-1] ))
                {
                    list.add( mapSpaces[x][y - 1] );
                    previous[x][y-1] = l;
                }
            }
            
        }
        ArrayList<Location> path = new ArrayList<Location>();
        Location l = mapSpaces[l2x][l2y];
        while(l!=null) {
            path.add( l );
            l = previous[getX( l )][getY(l)];
        }
        return path;
        
        
    }


    public int getX( Location l )
    {
        for ( int i = 0; i < mapSpaces.length; i++ )
        {
            for ( int j = 0; j < mapSpaces.length; j++ )
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
            for ( int j = 0; j < mapSpaces.length; j++ )
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
