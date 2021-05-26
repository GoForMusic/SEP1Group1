package Model;

import java.util.ArrayList;

public class VIAClub
{
  private ArrayList<Player> players;
  private ArrayList<Match> matches;

  public VIAClub()
  {
    players = new ArrayList<>();
    matches = new ArrayList<>();
  }

  public void addPlayer(Player player)
  {
    players.add(player);
  }

  public void addMatch(Match match)
  {
    matches.add(match);
  }

  public ArrayList<Player> getPlayers()
  {
    return players;
  }

  public ArrayList<Match> getMatches()
  {
    return matches;
  }

 public String toString()
  {
    return "VIAClub" + "players=" + players + ", matches=" + matches;
  }
  public boolean equals(Object obj)
  {
    if(!(obj instanceof VIAClub))
    {
      return false;
    }
    VIAClub other = (VIAClub) obj;

    return players.equals(other.players) && matches.equals(other.matches);
  }
}
