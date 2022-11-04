public class Tile {
    private Turnip crop;
    private boolean isPlowed;
    private boolean isWithered;
    private boolean isHarvestable;
    private int dayCount;

    public Tile() {
        this.crop = null;
        this.isPlowed = false;
        this.isWithered = false;
        this.isHarvestable = false;
        this.dayCount = 1;
    }

    public int getDayCount() {
        return this.dayCount;
    }

    public int advanceDay() {
        return this.dayCount = this.dayCount + 1;
    }
    
    public String display() {
        
        String tile = "⧈";

        if(this.isPlowed)
            tile = "▧";

        if(this.isPlowed && this.crop != null) {
            if(setHarvestable())
                tile = "♧";
            else if(setWithered())
                tile = "⊠";
            else
                tile = "⊡"; 
        }

        return tile;
    }
    
    public Turnip getCrop() {
        return crop;
    }
    
    public void setCrop(Turnip crop) {
        this.crop = crop;
    }
    
    public boolean setPlowed() {
        return isPlowed = true;
    }
    
    public boolean setWithered() {
        return isWithered = true;
    }

    public boolean setHarvestable() {
        return isHarvestable = true;
    }

    
}
