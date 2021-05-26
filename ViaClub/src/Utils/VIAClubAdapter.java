package Utils;

import Model.Match;
import Model.Player;

import java.util.ArrayList;

/**
 * A class that will manage all the details of player and match on binary file
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class VIAClubAdapter
{
  private String playersFileName;
  private String matchesFileName;
  private String xmlFileName;
  private MyFileHandler MyFileIO;

  /**
   * A three-argument constructor
   * @param playersFileName the binary file name where the players will be stored
   * @param matchesFileName the binary file name where the matches will be stored
   * @param xmlFileName the text file name where will be exported as xml format all the matches for website
   */
  public VIAClubAdapter(String playersFileName, String matchesFileName, String xmlFileName)
  {
    this.matchesFileName=matchesFileName;
    this.playersFileName=playersFileName;
    this.xmlFileName=xmlFileName;
    MyFileIO = new MyFileHandler();
  }

  /**
   * A method that will return all the players in a list from binary file
   * @return a list of players that are stored in the binary file
   */
  public ArrayList<Player> getAllPlayers()
  {
    ArrayList<Player> players = new ArrayList<Player>();
    players = (ArrayList<Player>)MyFileIO.readObjectFromFile(playersFileName);
    return players;
  }

  /**
   * A method that will return all the matches in a list from binary file
   * @return a list of matches that are stored in the binary file
   */
  public ArrayList<Match> getAllMatches()
  {
    ArrayList<Match> matches=new ArrayList<Match>();
    matches = (ArrayList<Match>)MyFileIO.readObjectFromFile(matchesFileName);
    return matches;
  }

  /**
   * A method that will return all the players in a list from binary file with a specific status
   * @param status the specific status
   * @return a list of players that are stored in the binary file with the specific status
   */
  public ArrayList<Player> getAllPlayersStatus(String status)
  {
    ArrayList<Player> players=getAllPlayers();
    ArrayList<Player> playersStatus = new ArrayList<Player>();
    for(int i=0;i<players.size();i++)
    {
      if(players.get(i).getStatus().equals(status))
      {
        playersStatus.add(players.get(i));
      }
    }

    return playersStatus;
  }

  /**
   * A method that will return all the matches in a list from binary file with a specific type
   * @param type the specific type of match
   * @return a list of matches that are stored in the binary file with the specific type
   */
  public ArrayList<Match> getAllMatchesType(String type)
  {
    ArrayList<Match> matches=getAllMatches();
    ArrayList<Match> matchesByType = new ArrayList<Match>();
    for(int i=0;i<matches.size();i++)
    {
      if (matches.get(i).getType().equals(type))
      {
        matchesByType.add(matches.get(i));
      }
    }

    return matchesByType;
  }

  /**
   * A method that will remove a player and store the update in binary file
   * @param obj the object that will be removed
   */
  public void removePlayer(Player obj)
  {
    ArrayList<Player> players = getAllPlayers();
    if(players.contains(obj))
    {
      players.remove(obj);
    }
    MyFileIO.writeObjectToFile(playersFileName,players);
  }

  /**
   * A method that will remove a match and store the update in binary file
   * @param obj the object that will be removed
   */
  public void removeMatch(Match obj)
  {
    ArrayList<Match> matches = getAllMatches();
    if(matches.contains(obj))
    {
      matches.remove(obj);
    }
    MyFileIO.writeObjectToFile(matchesFileName,matches);
  }

  /**
   * A method that will update the player and store the update to binary file
   * @param index the position in the list of the object that will be updated
   * @param obj the object new details
   */
  public void editPlayer(Player obj, int index)
  {
    ArrayList<Player> players = getAllPlayers();
    players.set(index, obj);
    MyFileIO.writeObjectToFile(playersFileName,players);
  }

  /**
   * A method that will update the match and store the update to binary file
   * @param obj the object new details
   * @param index the position in the list of the object that will be updated
   */
  public void editMatch(Match obj, int index)
  {
    ArrayList<Match> matches = getAllMatches();
    matches.set(index, obj);
    MyFileIO.writeObjectToFile(matchesFileName,matches);
  }

  /**
   * A method that will save a player in the binary file
   * @param obj the new player object
   */
  public void savePlayer(Player obj)
  {
    ArrayList<Player> localList = getAllPlayers();
    localList.add(obj);
    MyFileIO.writeObjectToFile(playersFileName, localList);
  }

  /**
   * A method that will save a list of players in the binary file
   * @param players the list of players that will be saved in the binary file
   */
  public void savePlayers(ArrayList<Player> players)
  {
      MyFileIO.writeObjectToFile(playersFileName, players);
  }

  /**
   * A method that will save a match in the binary file
   * @param obj the new match object
   */
  public void saveMatch(Match obj)
  {
    ArrayList<Match> localList = getAllMatches();
    localList.add(obj);
    saveMatches(localList);
  }

  /**
   * A method that will save a list of matches in the binary file
   * @param matches the list of matches that will be saved in the binary file
   */
  public void saveMatches(ArrayList<Match> matches)
  {
      MyFileIO.writeObjectToFile(matchesFileName, matches);
  }

  /**
   * A method that will save a list of matches in the text file
   * @param matches the list of matches that will be saved in the text file
   */
  public void exportMatches(ArrayList<Match> matches)
  {
    MyFileIO.writeToXmlFile(matches,xmlFileName);
  }

}
