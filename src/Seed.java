/**
 * This class represents a seed in the game. It contains the name and cost of the seed,
 * @version 1.0
 */
public class Seed {
    private String name;
    private double cost;

    /**
     * Constructor that creates a seed object depending on the seed's name
     * @param name the name of the seed
     */
    public Seed(String name){
        switch(name){
            case "Turnip" :
                this.name = "Turnip";
                this.cost = 5.00;
                break;
            case "Carrot" :
                this.name = "Carrot";
                this.cost = 10.00;
                break;
        }
    }

    /**
     * Gets the name of the seed
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the cost of the seed
     * @return cost
     */
    public double getCost() {
        return this.cost;
    }
}
