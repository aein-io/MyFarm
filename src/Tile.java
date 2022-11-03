public class Tile {
    private Turnip crop;
    private int dayCount;
    private boolean isPlowed;
    private boolean status;

    public Tile() {
        this.isPlowed = false;
    }

    public boolean isWithered(int dayCount, Turnip crop) {
        return true;
    }

    public boolean isHarvestable(int dayCount, Turnip crop) {
        return true;
    }

    public void isPlowed(boolean status) {

    }

    public void setPlowed(boolean newStatus) {
        this.isPlowed = newStatus;
    }

    public int returnCrop(Turnip crop) {
        return 5;
    }
}
