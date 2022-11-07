import java.util.ArrayList;

/**
 * This class represents a farm plot made of tiles that the player has.
 * 
 * @version 1.0
 */
public class FarmPlot {
    private Tile[][] tiles;
    private ArrayList<Seed> availableSeeds;
    private String farmName;

    /**
     * Constructor that creates and initializes a farm plot.
     * 
     * @param name the name of the farm
     * @param row the number of rows of the farm
     * @param col the number of columns of the farm
     */
    public FarmPlot(String name, int row, int col) {
        this.tiles = new Tile[row][col];

        // Initialize tiles
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

    /**
     * Gets a specific tile from the farm plot.
     * 
     * @param row the row where the tile is located
     * @param col the column where the tile is located
     * @returns the specific tile given a row and column
     */
    public Tile getTile(int row, int col) {
        return this.tiles[row][col];
    }

    /**
     * Returns the available seeds in the game.
     * 
     * @returns a Seed array list of available seeds 
     */
    public ArrayList<Seed> getAvailableSeeds() {
        return this.availableSeeds;
    }

    /**
     * Displays all the available seeds in the game and their costs.
     */
    public void printAvailableSeeds() {
        System.out.println("< Seed Store >");
        for (int index = 0; index < this.availableSeeds.size(); index++) {
            System.out.println((index) + ". " + this.availableSeeds.get(index).getName() + " $" + this.availableSeeds.get(index).getCost());
        }
    }

    /**
     * Advances the day in the game and checks if a player can continue playing.
     * 
     * @param farmer the player of the game
     * @returns true if the player has not yet lost the game, false otherwise
     */
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

        // Check if no active crops are growing
        for (Tile[] row : this.tiles) {
            for (Tile tile : row) {
                if (tile.getCrop() != null) {
                    noCrop = false;
                    break;
                }
            }
        }

        // Check if the farmer cannot buy the cheapest seed available
        for (Seed seed : this.availableSeeds) {
            if (farmer.getObjectCoins() < seed.getCost()) {
                break;
            }
            noMoney = false;
        }

        // Check if all tiles are withered
        for (Tile[] row : this.tiles) {
            for (Tile tile : row) {
                if(!tile.getTileStatus().isWithered()){
                    allWithered = false;
                    break;
                }
            }
        }
        
        // Check if farmer cannot afford the shovel
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

    /**
     * Method that prints the farm plot tiles
     */
    public void displayFarmPlot() {
        for(Tile[] row : this.tiles){
            for(Tile tile : row){
                System.out.print(tile.display());
            }
        }
    }
}