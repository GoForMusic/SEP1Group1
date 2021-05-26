package Main;

import Model.*;
import Utils.VIAClubAdapter;


/**
 * The Main.InitialData class initializes 4 players and 4 matches.After running it, the Main.Main class should be run so the GUI will start.
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class InitialData {
    public static void main(String[] args)
    {
        VIAClub viaClub = new VIAClub();
        VIAClubAdapter adapter = new VIAClubAdapter("players.bin","matches.bin","matches.xml");

        Player player = new Player("Adrian","Militaru",69,"Center","Injured");
        Player player2 = new Player("Adrian","Pompierescu",2,"Gate","Available");
        Player player3 = new Player("Grabiel","Muntinho",45,"LeftWing","Available");
        Player player4 = new Player("Freja","Hansen",55,"GoalKeeper","Suspended");

        viaClub.addPlayer(player);
        viaClub.addPlayer(player2);
        viaClub.addPlayer(player3);
        viaClub.addPlayer(player4);

        adapter.savePlayers(viaClub.getPlayers());

        Match match1=new Match(new MyDate(24,05,2021),"VIAClub","Barcelona",new Location("Denmark","Horsens","Horsens casa arena"),"Friendly");
        Match match2=new Match(new MyDate(25,05,2021),"Barcelona","VIAClub",new Location("Spain","Barcelona","Camp Nou"),"Friendly");

        viaClub.addMatch(match1);
        viaClub.addMatch(match2);

        adapter.saveMatches(viaClub.getMatches());

        System.out.println("Files have been saved");

    }
}
