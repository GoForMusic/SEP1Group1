package Model;

import java.util.ArrayList;

public class Match
{
  private String team1;
  private String team2;
  private String type;
  private String score;
  private ArrayList<Player> listOfPlayers;
  private MyDate date;
  private Location location;

  public Match(MyDate date, String team1, String team2, Location location,
      String type, String score, ArrayList<Player> listOfPlayers)
  {
    this.team1 = team1;
    this.team2 = team2;
    this.type = type;
    this.score = score;
    listOfPlayers = new ArrayList<>();
    this.date = date.copy();
    this.location = location.copy();
  }

  public void setdate(MyDate date)
  {
    this.date = date;
  }

  public void setTeam1(String team1)
  {
    this.team1 = team1;
  }

  public void setTeam2(String team2)
  {
    this.team2 = team2;
  }

  public void setLocation(Location location)
  {
    this.location = location;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public void setScore(int team1Score, int team2Score)
  {
    this.score = team1Score + "-" + team2Score;
  }

  public MyDate getDate()
  {
    return date;
  }

  public String getTeam1()
  {
    return team1;
  }

  public String getTeam2()
  {
    return team2;
  }

  public Location getLocation()
  {
    return location;
  }

  public String getType()
  {
    return type;
  }

  public String getScore()
  {
    return score;
  }

  public void addPlayer(Player player)
  {
    listOfPlayers.add(player);
  }

  public ArrayList<Player> getListOfPlayers()
  {


    return listOfPlayers;
  }

  public String toString()
  {
    return "Match" + "team1='" + team1 + '\'' + ", team2='" + team2 + '\''
        + ", type='" + type + '\'' + ", score='" + score + '\''
        + ", listOfPlayers=" + listOfPlayers + ", date=" + date + ", location="
        + location;
  }

  public boolean equals(Object obj)
  {

    if (!(obj instanceof Match))
    {
      return false;
    }
    Match other = (Match) obj;

    return date.equals(other.date) && team1.equals(other.team1) && team2
        .equals(other.team2) && location.equals(other.location) && type
        .equals(other.type) && score.equals(other.score) && listOfPlayers
        .equals(other.listOfPlayers);
  }

}

