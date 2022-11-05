import java.util.ArrayList;

public class FarmPlot {
    private Tile[][] tiles;
    private ArrayList<Seed> availableSeeds;
    private String farmName;

    public FarmPlot(String name, int row, int col) {
        this.tiles = new Tile[row][col];

        // initialize tiles
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.tiles[i][j] = new Tile();
            }
        }
        
        this.availableSeeds = new ArrayList<Seed>();
        availableSeeds.add(new Seed("Turnip"));
        availableSeeds.add(new Seed("Carrot"));

        this.farmName = name;
    }

    public ArrayList<Seed> getAvailableSeeds() {
        return this.availableSeeds;
    }

    public void printAvailableSeeds() {
        System.out.println("Available seeds:");
        for (int index = 0; index < this.availableSeeds.size(); index++) {
            System.out.println((index) + ". " + this.availableSeeds.get(index).getName() + " $" + this.availableSeeds.get(index).getCost());
        }
    }

    public boolean advanceDay(Farmer farmer){

        boolean gameOver = false;

        boolean noCrop = true;
        boolean noMoney = true;
        boolean allWithered = true;
        boolean noShovelMoney = true;


        for (Tile[] row : this.tiles) {
            for (Tile tile : row) {
                tile.updateTile();
            }
        }

        // check if no active crops are growing
        for (Tile[] row : this.tiles) {
            for (Tile tile : row) {
                if (tile.getCrop() != null) {
                    noCrop = false;
                    break;
                }
            }
        }

        // check if the farmer cannot buy the cheapest seed available
        for (Seed seed : this.availableSeeds) {
            if (farmer.getObjectCoins() < seed.getCost()) {
                break;
            }
            noMoney = false;
        }

        // check if all tiles are withered
        for (Tile[] row : this.tiles) {
            for (Tile tile : row) {
                if(!tile.getTileStatus().isWithered()){
                    allWithered = false;
                    break;
                }
            }
        }
        
        // check if farmer cannot afford the shovel
        if(farmer.getObjectCoins() > 7.0){
            noShovelMoney = false;
        }


        if ((noCrop && noMoney) || (allWithered && noShovelMoney) ) {
            gameOver = true;
        }

        if(gameOver)
            return false;

        return true;
    }

    public Tile getTile(int i, int j) {
        return this.tiles[i][j];
   }

    public void displayFarmPlot() {
        for(Tile[] row : this.tiles){
            for(Tile tile : row){
                System.out.print(tile.display());
            }
        }
    }
}
