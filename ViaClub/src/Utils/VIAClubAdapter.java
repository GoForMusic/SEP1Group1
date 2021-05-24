package Utils;

import Model.Match;
import Model.Player;
import Model.VIAClub;

import java.util.ArrayList;

public class VIAClubAdapter
{
  private String playersFileName;
  private String matchesFileName;
  private String xmlFileName;
  private MyFileHandler MyFileIO;

  public VIAClubAdapter(String playersFileName, String matchesFileName, String xmlFileName)
  {
    this.matchesFileName=matchesFileName;
    this.playersFileName=playersFileName;
    this.xmlFileName=xmlFileName;
    MyFileIO = new MyFileHandler();
  }

  public ArrayList<Player> getAllPlayers()
  {
    ArrayList<Player> players;
    players = (ArrayList<Player>)MyFileIO.readObjectFromFile(playersFileName);
    return players;
  }

  public ArrayList<Match> getAllMatches()
  {
    ArrayList<Match> matches;
    matches = (ArrayList<Match>)MyFileIO.readObjectFromFile(matchesFileName);
    return matches;
  }

  public ArrayList<Player> getAllPlayersStatus(String status)
  {
    ArrayList<Player> players;
    ArrayList<Player> playersStatus = new ArrayList<>();
    players=getAllPlayers();
    for(int i=0;i<players.size();i++)
    {
      if(players.get(i).getStatus().equals(status))
      {
        playersStatus.add(players.get(i));
      }
    }

    return players;
  }

  public ArrayList<Match> getAllMatchesType(String type)
  {
    ArrayList<Match> matches;
    ArrayList<Match> matchesByType = new ArrayList<>();
    matches=getAllMatches();
    for(int i=0;i<matches.size();i++)
    {
      if (matches.get(i).getType().equals(type))
      {
        matchesByType.add(matches.get(i));
      }
    }

    return matchesByType;
  }

  public void removePlayer(Player obj)
  {
    ArrayList<Player> players = getAllPlayers();
    if(players.contains(obj))
    {
      players.remove(obj);
    }
    MyFileIO.writeObjectToFile(playersFileName,players);
  }

  public void removeMatch(Match obj)
  {
    ArrayList<Match> matches = getAllMatches();
    if(matches.contains(obj))
    {
      matches.remove(obj);
    }
    MyFileIO.writeObjectToFile(matchesFileName,matches);
  }

  public void editPlayer(Player obj, int index)
  {
    ArrayList<Player> players = getAllPlayers();
    players.set(index, obj);
    MyFileIO.writeObjectToFile(playersFileName,players);
  }

  public void editMatch(Match obj, int index)
  {
    ArrayList<Match> matches = getAllMatches();
    matches.set(index, obj);
    MyFileIO.writeObjectToFile(matchesFileName,matches);
  }

  public void savePlayer(Player obj)
  {
    ArrayList<Player> players = getAllPlayers();
    if(!players.contains(obj))
    {
      players.add(obj);
    }
    MyFileIO.writeObjectToFile(matchesFileName,players);
  }

  public void savePlayers(ArrayList<Player> players)
  {
    ArrayList<Player> storedPlayers = getAllPlayers();

    if(storedPlayers==null)
    {
      MyFileIO.writeObjectToFile(playersFileName, players);
    }
    else
    {
      storedPlayers.addAll(players);
      MyFileIO.writeObjectToFile(playersFileName, storedPlayers);
    }
  }

  public void saveMatch(Match obj)
  {
    ArrayList<Match> matches = getAllMatches();
    if(!matches.contains(obj))
    {
      matches.add(obj);
    }
    MyFileIO.writeObjectToFile(matchesFileName,matches);
  }

  public void saveMatches(ArrayList<Match> matches)
  {
    ArrayList<Match> storedMatches = getAllMatches();

    if(storedMatches==null)
    {
      MyFileIO.writeObjectToFile(matchesFileName, matches);
    }
    else
    {
      storedMatches.addAll(matches);
      MyFileIO.writeObjectToFile(matchesFileName, storedMatches);
    }
  }

  public void exportMatches(ArrayList<Match> matches)
  {
    MyFileIO.writeToXmlFile(matches,xmlFileName);
  }

}
