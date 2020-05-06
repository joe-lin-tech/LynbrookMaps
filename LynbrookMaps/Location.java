import java.awt.image.*;

public class Location {

	private BufferedImage image;
	private boolean walkable;
	private boolean room;
	
	public Location(BufferedImage image, boolean walkable, boolean room) {
		this.image = image;
		this.walkable = walkable;
		this.room = room;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public boolean isRoom() {
		return room;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
