public class Team extends Card{
    public String name;
    public String Type = "TEAM";

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                ", name='" + name + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
    public String toFormattedDeck(){
        return name + "  " + Type;
    }
}
