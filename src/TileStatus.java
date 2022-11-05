public class TileStatus {
    private String reason;
    private boolean isAvailable;

    public TileStatus() {
        this.reason = null;
        this.isAvailable = true;
    }
    
    private void setStatus(String reason, boolean isAvailable) {
        this.reason = reason;
        this.isAvailable = isAvailable;
    }

    public void hasRock(){
        setStatus("The tile has a rock", false);
    }

    public void hasWithered(){
        setStatus("The tile has a withered crop", false);
    }

    public boolean isWithered(){

        if(this.reason != null){
            if(this.reason.equals("The tile has a withered crop"))
            return true;
        }
        
        return false;
    }

    public boolean checkHasCrop(){
        
        if(this.reason != null){
            if(this.reason.equals("The tile has a crop"))
                return true;
        }
        
        return false;
    }

    public void nearTree() {
        setStatus("The tile is near a tree", false);
    }

    public void hasCrop() {
        setStatus("The tile has a crop", false);
    }

    public String getReason() {
        return this.reason;
    }

    public boolean getAvailability() {
        return this.isAvailable;
    }
}
