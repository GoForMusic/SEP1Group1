package Utils;

import java.util.ArrayList;

public class VIAClubAdapter
{
  private String playersFileName;
  private String matchesFileName;
  private String xmlFileName;

  public VIAClubAdapter(String playersFileName, String matchesFileName, String xmlFileName)
  {
    this.matchesFileName=matchesFileName;
    this.playersFileName=playersFileName;
    this.xmlFileName=xmlFileName;
  }

  public ArrayList<Player> getAllPlayers()
  {
    return VIACLUB.getPlayers();
  }

  public ArrayList<Match> getAllMatches()
  {
    return VIACLUB.getMatches();
  }

  public ArrayList<Player> getAllPlayersStatus(String status)
  {
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> playersStatus = new ArrayList<Player>();
    players=getAllPlayers();
    for(int i=0;i<players.size();i++)
    {
      if(players.get(i).status.equals(status))
      {
        playersStatus.add(players.get(i));
      }
    }

    return players;
  }
}
