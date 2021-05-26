package Model;

import java.util.ArrayList;


/**
 * A class that include all the information about the Match and player
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class VIAClub
{
  private ArrayList<Player> players;
  private ArrayList<Match> matches;

  /**
   * A no-argument constructor that will only initialize the player and match list
   */
  public VIAClub()
  {
    players = new ArrayList<>();
    matches = new ArrayList<>();
  }

  /**
   * A method that will add a player to an array list
   * @param player reference to the player object that will be added into the array list
   */
  public void addPlayer(Player player)
  {
    players.add(player);
  }

  /**
   * A method that will add a match to an array list
   * @param match reference to the player object that will be added into the array list
   */
  public void addMatch(Match match)
  {
    matches.add(match);
  }

  /**
   * A method that will return the whole list of players
   * @return the list of players in ArrayList format
   */
  public ArrayList<Player> getPlayers()
  {
    return players;
  }

  /**
   * A method that will return the whole list of players
   * @return the list of players in ArrayList format
   */
  public ArrayList<Match> getMatches()
  {
    return matches;
  }

  /**
   * A method that will return a String with all the information from System about players and matches
   * @return all the information about players and matches that are stored in the system
   */
  public String toString()
  {
    return "VIAClub" + "players=" + players + ", matches=" + matches;
  }

  /**
   * A method that will verify if two different objects are matching
   * @param obj reference to the external object
   * @return true if everything is matching otherwise false
   */
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
