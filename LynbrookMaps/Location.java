import java.awt.image.*;

public class Location {

    private String imagePath;
    private boolean walkable;
    private boolean room;
    
    public Location(String imagePath, boolean walkable, boolean room) {
        this.imagePath = imagePath;
        this.walkable = walkable;
        this.room = room;
    }
    
    public boolean isWalkable() {
        return walkable;
    }
    
    public boolean isRoom() {
        return room;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
}

