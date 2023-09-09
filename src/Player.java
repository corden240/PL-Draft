import java.util.ArrayList;

public class Player extends Card{
    public String name;
    public String position;
    public double price;

    public Player(String name, String position, double price) {
        this.name = name;
        this.position = position;
        this.price = price;
    }

    public boolean roleMatch(String p){
        if (this.position == p)
            return true;
        else
            return false;
    }
    public boolean isInGoal(){
        if (this.position.equalsIgnoreCase("GK"))
            return true;
        else
            return false;
    }
    public boolean isInDefence(){
        if (this.position.equalsIgnoreCase("DEF"))
            return true;
        else
            return false;
    }
    public boolean isInMidfield(){
        if (this.position.equalsIgnoreCase("MID"))
            return true;
        else
            return false;
    }
    public boolean isInAttack(){
        if (this.position.equalsIgnoreCase("FWD"))
            return true;
        else
            return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", price=" + price +
                '}';
    }
    public String toFormattedDeck(){
        return name + " " + position;
    }
    public static void sort(ArrayList<Player> list) {

        list.sort((o1, o2)
                -> Integer.valueOf((int) o1.compareTo(o2)));
    }
    public double compareTo(Player comparestu) {
        double comparePrice = ((Player)comparestu).getPrice();
        //  For Ascending order
        return this.price - comparePrice;

    }
}
