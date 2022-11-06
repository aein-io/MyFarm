/**
 * Class that represents the status of a tile. 
 * It contains the availability of the tile and the reason when it is not.
 * @version 1.0
 */
public class TileStatus {
    private String reason;
    private boolean isAvailable;

    /**
     * Constructor that instantiates the TileStatus object
     */
    public TileStatus() {
        this.reason = null;
        this.isAvailable = true;
        
    }
    
    /**
     * Sets the availability of the tile and the reason when it is not 
     * @param reason the reason why the tile is not available
     * @param isAvailable checker if the tile is available
     */
    private void setStatus(String reason, boolean isAvailable) {
        this.reason = reason;
        this.isAvailable = isAvailable;
    }

    public void hasRock(){
        setStatus("The tile has a rock.", false);
    }

    public void hasWithered(){
        setStatus("The tile has a withered crop.", false);
    }

    public boolean isWithered(){
        if(this.reason != null){
            if(this.reason.equals("The tile has a withered crop."))
            return true;
        }
        
        return false;
    }

    /**
     * Sets the tile's inavailability due to its proximity to a tree
     */
    public void nearTree() {
        setStatus("The tile is near a tree", false);
    }

    /**
     * Sets the tile's inavailability because it is occupied by a crop
     */
    public void hasCrop() {
        setStatus("The tile has a crop", false);
    }

    /**
     * Checks if the tile has a crop
     * @returns true if the tile has a crop, and false otherwise
     */
    public boolean checkHasCrop(){
        if(this.reason != null){
            if(this.reason.equals("The tile has a crop"))
                return true;
        }
        
        return false;
    }

    /**
     * Gets the reason why the tile is not available
     * @return reason string that contains the reason for the tiles inavailability
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Gets the availability of the tile
     * @return isAvailable checker if the tile is available
     */
    public boolean getAvailability() {
        return this.isAvailable;
    }
}
