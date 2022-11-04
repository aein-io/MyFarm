public class Tile {

    private Turnip crop;
    private boolean isPlowed;
    private boolean isWithered;
    private boolean isHarvestable;
    private boolean isOccupied;
    private int dayCount;

    public Tile() {
        this.crop = null;
        this.isPlowed = false;
        this.isWithered = false;
        this.isHarvestable = false;
        this.isOccupied = false;
        this.dayCount = 1;
    }

    public String display() {

        String tile = "⧈";

        if(this.isPlowed) {
            tile = "▧";

            if(this.getCrop() != null) {
                if(this.isHarvestable())
                    tile = "♧";
                else if(this.isWithered())
                    tile = "⊠";
                else
                    tile = "⊡";
            }
        }

        return tile;
    }

    public int getDayCount() {
        return this.dayCount;
    }

    public int advanceDay() {
        return this.dayCount = this.dayCount + 1;
    }

    public void setPlowed() {
        this.isPlowed = true;
    }

    public void setOccupied() {
        this.isOccupied = true;
    }

    public Turnip getCrop() {

        return crop;
    }

    public void setCrop(Turnip crop) {
        this.crop = crop;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isHarvestable() {
        return isHarvestable;
    }

    public boolean isWithered() {
        return isWithered;
    }

}

