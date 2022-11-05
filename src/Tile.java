public class Tile {

    private Crop crop;
    private TileStatus availability;
    private boolean isPlowed;

    public Tile() {
        this.crop = null;
        this.isPlowed = false;
        this.availability = new TileStatus();
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public boolean isPlowed() {
        return this.isPlowed;
    }

    public void setPlowed(boolean plow) {
        this.isPlowed = plow;
    }

    public TileStatus getTileStatus() {
        return this.availability;
    }

    public boolean isAvailable(){
        return this.availability.getAvailability();
    }

    public void makeAvailable() {
        this.availability = new TileStatus();
    }

    public void updateTile() {

        // return if the tile does not have a crop
        if(this.crop == null)
            return;

        // update crop status
        this.crop.updatePlantedSince();

        // check if crop if harvest time
        boolean harvestDay = this.crop.getPlantedSince() == this.crop.getHarvestTime() ? true : false;

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