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

  public void removePlayer(Player player)
  {
    players.remove(player);
  }

  public void removeMatch(Match match)
  {
    matches.remove(match);
  }

  public Player getPlayers(int index)
  {
    return players.get(index);
  }

  public ArrayList<Player> getPlayers()
  {
    return players;
  }
  public Match getMatch(int index)
  {
    return matches.get(index);
  }

  public ArrayList<Match> getMatches()
  {
    return matches;
  }
  public void editPlayers(int index, Player player)
  {
    players.get(index).setFirstName(player.getFirstName());
    players.get(index).setLastName(player.getLastName());
    players.get(index).setPlayerNumber(player.getPlayerNumber());
    players.get(index).setPreferredPosition(player.getPreferredPosition());
    players.get(index).setStatus(player.getStatus());
  }
  public void editMatches(int index, Match match)
  {
  matches.get(index).setdate(match.getDate());
  matches.get(index).setLocation(match.getLocation());
  matches.get(index).setType(match.getType());
  matches.get(index).setTeam1(match.getTeam1());
  matches.get(index).setTeam2(match.getTeam2());
  matches.get(index).setScore(match.getScore());
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
