import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class database {
    public List<Card> Goalkeepers = new ArrayList<>(), Defence = new ArrayList<>(), Midfield = new ArrayList<>(), Attack = new ArrayList<>(), players = new ArrayList<>(), teams = new ArrayList<>();
    public List<Card> fullList = new ArrayList<>();
    public List<List<Card>> boosterBox = new ArrayList<>();
    static int GOALKEEPERS_IN_PACK = 2, DEFENDERS_IN_PACK = 3, MIDFIELDERS_IN_PACK = 3, FORWARDS_IN_PACK = 2, TEAMS_IN_PACK = 2, RANDOMS = 0, POSITIONS = 6;
    static int[] POSITION_IN_PACK = {GOALKEEPERS_IN_PACK, DEFENDERS_IN_PACK, MIDFIELDERS_IN_PACK, FORWARDS_IN_PACK, TEAMS_IN_PACK, RANDOMS};
    static boolean duplicates = true;

    public database() {
        this.fullList = importFromFile();       //when a database is created it automatically imports from the .txt file all the players and takes the manual teams.
        createRoleSubPacks();                   //this creates the sub-lists of players in their roles and a team list.
    }
    private static Player createPlayer(String[] metadata) { //Takes imported metadata and attributes it to a player card.
        String name = metadata[0];
        String position = metadata[1];
        double price = Double.valueOf(metadata[2]);

        // create and return player with this metadata
        return new Player(name, position, price);
    }
    private static Team createTeam(String[] metadata) { //Takes imported metadata and attributes it to a team card.
        String name = metadata[0];
        // create and return player with this metadata
        return new Team(name);
    }
    private static List<Card> importFromFile(){
        List<Card> allPlayersAndTeams = new ArrayList<>();
        try {
            List<String[]> data = new ArrayList<>();//list of lists to store data
            String file = "C:\\Charlie\\Excel Hobby\\Football Draft Game\\databaseplayers.txt";//file path
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //Reading until we run out of lines
            String line = br.readLine();
            while (line != null) {
                String[] lineData = line.split(",");//splitting lines
                data.add(lineData);
                line = br.readLine();
                Player player = createPlayer(lineData);
                allPlayersAndTeams.add(player);
            }
            br.close();

        } catch (Exception e) {
            System.out.print(e);
        }
        try {
            List<String[]> data = new ArrayList<>();
            String file = "C:\\Charlie\\Excel Hobby\\Football Draft Game\\databaseteams.txt";//file path
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //Reading until we run out of lines
            String line = br.readLine();
            while (line != null) {
                String[] lineData = line.split(",");//splitting lines
                data.add(lineData);
                line = br.readLine();
                Team team = createTeam(lineData);
                allPlayersAndTeams.add(team);
            }
            br.close();

        } catch (Exception e) {
            System.out.print(e);
        }
        return allPlayersAndTeams;
    }
    public void createRoleSubPacks(){
        for (int i = 0; i < this.fullList.size(); i++){
            Card element = this.fullList.get(i);
            if(element instanceof Player){
                Player temp = (Player)element;
                if (temp.isInGoal()) {
                    this.Goalkeepers.add(element);
                }
                if (temp.isInDefence()) {
                    this.Defence.add(element);
                }
                if (temp.isInMidfield()) {
                    this.Midfield.add(element);
                }
                if (temp.isInAttack()) {
                    this.Attack.add(element);
                }
            }
            else if(element instanceof Team){
                this.teams.add(element);
            }
        }
    }
    public void createXPacks(int x){
       for(int z = 0; z < x; z++) {
           List<Card> pack = new ArrayList<>();
           int nextRN = 0;

           for (int j = 0; j < POSITIONS; j++) {
               for (int i = 0; i < POSITION_IN_PACK[j]; i++) {

                   if (j == 0) {
                       nextRN = (int) ((Math.random() * (Goalkeepers.size())));
                       pack.add(Goalkeepers.get(nextRN));
                       if (!duplicates)
                           Goalkeepers.remove(Goalkeepers.get(nextRN));
                   }
                   if (j == 1) {
                       nextRN = (int) ((Math.random() * (Defence.size())));
                       pack.add(Defence.get(nextRN));
                       if (!duplicates)
                           Defence.remove(Defence.get(nextRN));
                   }
                   if (j == 2) {
                       nextRN = (int) ((Math.random() * (Midfield.size())));
                       pack.add(Midfield.get(nextRN));
                       if (!duplicates)
                           Midfield.remove(Midfield.get(nextRN));
                   }
                   if (j == 3) {
                       nextRN = (int) ((Math.random() * (Attack.size())));
                       pack.add(Attack.get(nextRN));
                       if (!duplicates)
                           Attack.remove(Attack.get(nextRN));
                   }
                   if (j == 4) {
                       nextRN = (int) ((Math.random() * (teams.size())));
                       pack.add(teams.get(nextRN));
                       if (!duplicates)
                           teams.remove(teams.get(nextRN));
                   }
                   if (j == 5) {
                       nextRN = (int) ((Math.random() * (fullList.size())));
                       pack.add(fullList.get(nextRN));
                       if (!duplicates)
                           fullList.remove(fullList.get(nextRN));
                   }
               }
           }
            this.boosterBox.add(pack);
       }
    }
    public void printPacks(){
        System.out.println("----------------");
        for (int i = 0; i < boosterBox.size(); i++) {
            for (int j = 0; j < boosterBox.get(i).size(); j++) {
                Card element = boosterBox.get(i).get(j);
                if (element instanceof Player)
                    System.out.println(((Player) element).toFormattedDeck());
                if (element instanceof Team)
                    System.out.println(((Team) element).toFormattedDeck());
            }
            System.out.println("----------------");
        }
    }
}