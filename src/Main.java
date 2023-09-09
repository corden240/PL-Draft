//notes:
//  What to do on a blank gameweek for a team, cant have their team or their players? just don't play?

public class Main {
    static int NUM_PLAYERS = 3;
    public static void main(String[] args) {

        //Creates a database of cards from imports and manually written teams
        //Creates sub-lists of players in certain positions and a list of teams
        database fullDatabase = new database();

        //Creates and makes NUM_PLAYERS number of packs from the database.
        fullDatabase.createXPacks(NUM_PLAYERS);

        //Then it prints the packs in a useful format
        fullDatabase.printPacks();
    }
}