import java.util.ArrayList;

/**
 * Description of class
 *
 * @author
 */
public class Farmer {
    private String name;
    private int exp, level;
    private FarmerLevel farmerLevel;
    private double objectCoins;
    private ArrayList<Seed> availableSeeds;
    private int plantedSeeds, harvestedCrops;
    private Tool plow, wateringCan, fertilizer, pickaxe, shovel;

    /**
     *
     */
    public Farmer() {
        this.name = "Jack"; // Default name
        this.availableSeeds = new ArrayList<>();
    }

    /**
     *
     * @param name
     */
    public Farmer(String name) {
        this.name = name;
        this.availableSeeds = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return
     */
    public int getExp() {
        return this.exp;
    }

    /**
     *
     * @param exp
     */
    public void addExp(int exp) {

    }

    /**
     *
     */
    public void addCoins() {

    }

    /**
     *
     * @param name
     * @param availableSeeds
     */
    public void buySeeds(String name, ArrayList<Seed> availableSeeds) {

    }

    /**
     *
     * @param name
     */
    public void plantCrop(String name) {

    }

    /**
     *
     * @param currentTile
     */
    public void harvestCrop(Tile currentTile) {

    }
}
