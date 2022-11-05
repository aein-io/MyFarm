public class Seed {
    private String name;
    private double cost;

    public Seed(String name){
        switch(name){
            case "Turnip" :
                this.name = "Turnip";
                this.cost = 6.00;
                break;
            case "Carrot" :
                this.name = "Carrot";
                this.cost = 9.00;
                break;
        }
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }
}
