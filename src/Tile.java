/**
 * This class represents a single tile on the farm.
 * 
 * @version 1.0
 */
public class Tile {

    private Crop crop;
    private boolean isPlowed;
    private TileStatus availability;

    /**
     * Constructor that initializes the tile.
     */
    public Tile() {
        this.crop = null;
        this.isPlowed = false;
        this.availability = new TileStatus();
    }

    /**
     * Gets the crop on the tile.
     * 
     * @return the crop on the tile
     */
    public Crop getCrop() {
        return this.crop;
    }

    /**
     * Gets the status of the tile.
     * 
     * @return availability of the tile from TileStatus
     */
    public TileStatus getTileStatus() {
        return this.availability;
    }

    /**
     * Makes the tile available by initializing a new and default TileStatus.
     */
    public void makeAvailable() {
        this.isPlowed = false;
        this.availability = new TileStatus();
    }

    /**
     * Checks if tile is available.
     * 
     * @return true if tile is available, false otherwise
     */
    public boolean isAvailable(){
        return this.availability.getAvailability();
    }
    
    /**
     * Checks if tile is plowed.
     * 
     * @return true if tile is plowed, false otherwise
     */
    public boolean isPlowed() {
        return this.isPlowed;
    }

    /**
     * Sets the status of the tile as plowed.
     * 
     * @param plow true if tile is plowed, false otherwise
     */
    public void setPlowed(boolean plow){
        this.isPlowed = plow;
    }

    /**
     * Changes the crop currently planted on the tile.
     * 
     * @param crop the crop that is set on a tile
     */
    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    /**
     * Updates all necessary attributes on the tile. Called when the day has ended and crops must grow.
     */
    public void updateTile() {

        // Return if the tile does not have a crop
        if(this.crop == null)
            return;

        // Update crop status
        this.crop.updatePlantedSince();

        // Check if it is crop's harvest time
        boolean harvestDay = this.crop.plantedWhen() == this.crop.getHarvestTime() ? true : false;

        // Check if needs are met
        boolean waterNeedsMet = this.crop.getTimesWatered() >= this.crop.getWaterNeeds() ? true : false;
        boolean fertilizerNeedsMet = this.crop.getTimesFertilized() >= this.crop.getFertilizerNeeds() ? true : false;


        if(harvestDay && waterNeedsMet && fertilizerNeedsMet) {
            this.crop.setHarvestable(true);
            return;
        }

        if(harvestDay && (!waterNeedsMet || !fertilizerNeedsMet) || this.crop.plantedWhen() > this.crop.getHarvestTime()) {
            this.crop.wither();
            this.availability.hasWithered();
            this.crop.setHarvestable(false);
            return;
        }
    }

    /**
     * Creates and returns a visual representation of a tile and its current state.
     * 
     * @return a string of symbols representing the tile's state
     */
    public String display() {

        String display = "🟩";

        if(this.isPlowed) {
            display = "🟫";
        
            if(this.availability.checkHasCrop()){
                display = "🌱";
                if(this.getCrop().isHarvestable())
                    display = "🌾\n\nYour crop is ready for harvest!";
            }
        }

        if(this.availability.isWithered())
            display = "💀\n\nYour crop withered overnight.";

        return display;
    }
}