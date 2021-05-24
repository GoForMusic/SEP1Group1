package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that include all the information about the Match
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class Match implements Serializable
{
  private String team1;
  private String team2;
  private String type;
  private String score;
  private ArrayList<Player> listOfPlayers;
  private MyDate date;
  private Location location;

  /**
   * A five-argument constructor with a date, team1 name, team2 name, location and the match type
   * @param date a copy of the date when the match will be
   * @param team1 home team name
   * @param team2 visiting team name
   * @param location a copy of the location where the match will be
   * @param type the match type (friendly, league and)
   * @variable score the score by default will be set to "0-0"
   * @variable listOfPlayers will initialize the list of players who will play in the specific match
   */
  public Match(MyDate date, String team1, String team2, Location location, String type)
  {
    this.team1 = team1;
    this.team2 = team2;
    this.type = type;
    this.score = "0 - 0";
    this.listOfPlayers = new ArrayList<>();
    this.date = date.copy();
    this.location = location.copy();
  }

  /**
   * A method that will set the date
   * @param date the new date for match
   */
  public void setDate(MyDate date)
  {
    this.date = date;
  }

  /**
   * A method that will set the team1 (home team) name
   * @param team1 set the new team name
   */
  public void setTeam1(String team1)
  {
    this.team1 = team1;
  }

  /**
   * A method that will set the team2 (visiting team) name
   * @param team2 set the new team name
   */
  public void setTeam2(String team2)
  {
    this.team2 = team2;
  }

  /**
   * A method that will set the new location
   * @param location set new location
   */
  public void setLocation(Location location)
  {
    this.location = location;
  }

  /**
   * A method that will set the new match type
   * @param type set the match type
   */
  public void setType(String type)
  {
    this.type = type;
  }

  /**
   * A method with two-arguments that will set the score as an String
   * @param team1Score home team score
   * @param team2Score visithing team score
   */
  public void setScore(int team1Score, int team2Score)
  {
    this.score = team1Score + "-" + team2Score;
  }

  /**
   * A method with one argument that will set the score with the new one
   * @param score the new score
   */
  public void setScore(String score)
  {
    this.score = score;
  }

  /**
   * A method that will return the match date
   * @return the match date
   */
  public MyDate getDate()
  {
    return date;
  }

  /**
   * A method that will return the team1 name (home team)
   * @return the team1 name (home team)
   */
  public String getTeam1()
  {
    return team1;
  }

  /**
   * A method that will return the team2 name (visiting team)
   * @return the team2 name (visiting team)
   */
  public String getTeam2()
  {
    return team2;
  }

  /**
   * A method that will return the match location
   * @return the match location
   */
  public Location getLocation()
  {
    return location;
  }

  /**
   * A method that will return the match type
   * @return the match type
   */
  public String getType()
  {
    return type;
  }

  /**
   * A method that will return the match score
   * @return the match score
   */
  public String getScore()
  {
    return score;
  }

  /**
   * A method that will add a player to the list
   * @param player add the specific player to listOfPlayers
   */
  public void addPlayer(Player player)
  {
    listOfPlayers.add(player);
  }

  /**
   * A method that will get the list of players who are playing in the match
   * @return the player list with all the players who are playing in the specific match
   */
  public ArrayList<Player> getListOfPlayers()
  {


    return listOfPlayers;
  }

  /**
   * A method that will return a String with all the details of a match
   * @return with all the details of a match
   */
  public String toString()
  {
    return "Match" + "team1='" + team1 + '\'' + ", team2='" + team2 + '\''
        + ", type='" + type + '\'' + ", score='" + score + '\''
        + ", listOfPlayers=" + listOfPlayers + ", date=" + date + ", location="
        + location;
  }

  /**
   * A method that will compare if another object is equal with the current match
   * @param obj the other object that need to be verify if is same or not with the current match
   * @return true if everything is matching and false if something is not equal
   */
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

