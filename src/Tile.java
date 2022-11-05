/**
 * This class represents a single tile on the farm
 * @version 1.0
 */
public class Tile {

    private Crop crop;
    private boolean isPlowed;
    private TileStatus availability;

    /**
     * Constructor that initializes the tile
     */
    public Tile() {
        this.crop = null;
        this.isPlowed = false;
        this.availability = new TileStatus();
    }

    /**
     * Gets the crop on the tile
     * @return crop the crop on the tile
     */
    public Crop getCrop() {
        return this.crop;
    }

    /**
     * Gets the status of the tile
     * @return availability the TileStatus of the tile
     */
    public TileStatus getTileStatus() {
        return this.availability;
    }

    /**
     * Makes the tile available 
     */
    public void makeAvailable() {
        this.availability = new TileStatus();
    }

    /**
     * @return The tile's availability status
     */
    public boolean isAvailable(){
        return this.availability.getAvailability();
    }
    
    /**
     * @return Whether the tile is currently plowed or not
     */
    public boolean isPlowed() {
        return this.isPlowed;
    }

    /**
     * @param plow The plow status to change to
     */
    public void setPlowed(boolean plow){
        this.isPlowed = plow;
    }

    /**
     * Changes the crop currently planted on the tile
     * @param crop The crop to set
     */
    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    /**
     * Updates all necessary attributes on the tile. Called when the day has ended and crops must get older.
     */
    public void updateTile() {

        // return if the tile does not have a crop
        if(this.crop == null)
            return;

        // update crop status
        this.crop.updatePlantedSince();

        // check if crop if harvest time
        boolean harvestDay = this.crop.plantedWhen() == this.crop.getHarvestTime() ? true : false;

        // check if needs are met
        boolean waterNeedsMet = this.crop.getTimesWatered() >= this.crop.getWaterNeeds() ? true : false;
        boolean fertilizerNeedsMet = this.crop.getTimesFertilized() >= this.crop.getFertilizerNeeds() ? true : false;


        if(harvestDay && waterNeedsMet && fertilizerNeedsMet) {
            this.crop.setHarvestable(true);
            return;
        }

        if(harvestDay && (!waterNeedsMet || !fertilizerNeedsMet)) {
            this.crop.wither();
            this.availability.hasWithered();
            this.crop.setHarvestable(false);
            return;
        }
    }

    /**
     * Creates and returns a visual representation of a tile and its current state.
     * @return A string of symbols representing the tile.
     */
    public String display() {

        String display = "❇️";

        if(this.isPlowed) {
            display = "🟫";
        
            if(this.availability.checkHasCrop()){
                display = "🌱";
                if(this.getCrop().isHarvestable())
                    display = "🌾";
            }
        }

        if(this.availability.isWithered())
            display = "🍃";

        return display;
    }
}