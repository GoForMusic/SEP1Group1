package Utils;

import Model.Match;
import Model.Player;
import Model.VIAClub;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    ArrayList<Player> players = new ArrayList<Player>();
    players = (ArrayList<Player>)MyFileIO.readObjectFromFile(playersFileName);
    return players;
  }

  public ArrayList<Match> getAllMatches()
  {
    ArrayList<Match> matches=new ArrayList<Match>();
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
    ArrayList<Player> localList = getAllPlayers();
    localList.add(obj);
    MyFileIO.writeObjectToFile(playersFileName, localList);
  }

  public void savePlayers(ArrayList<Player> players)
  {
      MyFileIO.writeObjectToFile(playersFileName, players);
  }

  public void saveMatch(Match obj)
  {
    ArrayList<Match> localList = getAllMatches();
    localList.add(obj);
    saveMatches(localList);
  }

  public void saveMatches(ArrayList<Match> matches)
  {
      MyFileIO.writeObjectToFile(matchesFileName, matches);
  }

  public void exportMatches(ArrayList<Match> matches)
  {
    MyFileIO.writeToXmlFile(matches,xmlFileName);
  }

}
