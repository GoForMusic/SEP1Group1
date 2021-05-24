import Model.Location;
import Model.Match;
import Model.MyDate;
import Model.Player;
import Utils.MyFileHandler;
import Utils.VIAClubAdapter;

import java.util.ArrayList;

public class InitialData {
    public static void main(String[] args)
    {
        VIAClubAdapter adapter = new VIAClubAdapter("players.bin","matches.bin","matches.xml");
        ArrayList<Player> players = new ArrayList<Player>();

        Player player = new Player("Adrian","Militaru",69,"Center","Injured");
        Player player2 = new Player("Adrian","Pompierescu",2,"Gate","Available");
        Player player3 = new Player("Grabiel","Muntinho",45,"LeftWing","Available");
        Player player4 = new Player("Freja","Hansen",55,"GoalKeeper","Suspended");

        players.add(player);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        adapter.savePlayers(players);

        ArrayList<Match> matches = new ArrayList<Match>();

        Match match1=new Match(new MyDate(24,05,2021),"VIAClub","Barcelona",new Location("Denmark","Horsens","Horsens casa arena"),"Friendly");
        Match match2=new Match(new MyDate(25,05,2021),"Barcelona","VIAClub",new Location("Spain","Barcelona","Camp Nou"),"Friendly");

        matches.add(match1);
        matches.add(match2);

        adapter.saveMatches(matches);

        System.out.println("Files have been saved");

    }
}
