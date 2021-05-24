package Model;

import java.io.Serializable;

/**
 * A class that include all the information about the Player
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class Player implements Serializable
{
  private String firstName;
  private String lastName;
  private int playerNumber;
  private String preferredPosition;
  private String status;

  /**
   * A five-argument constructor with first name, last name, player number, preferred position and the player status
   * @param firstName the first name of the player
   * @param lastName the last name of the player
   * @param playerNumber the player number
   * @param preferredPosition the preferred position
   * @param status the player status "Available" or "Suspended" or "Injured"
   */
  public Player(String firstName, String lastName, int playerNumber,
      String preferredPosition, String status)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.playerNumber = playerNumber;
    this.preferredPosition = preferredPosition;
    this.status = status;
  }

  /**
   * A method that will set the new player number
   * @param playerNumber the new player number
   */
  public void setPlayerNumber(int playerNumber)
  {
    this.playerNumber = playerNumber;
  }

  /**
   * A method that will set the first name of the player
   * @param firstName the new first name
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * A method that will set the last name of the player
   * @param lastName the new last name
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * A method that will set the preferred position
   * @param preferredPosition set the new position
   */
  public void setPreferredPosition(String preferredPosition)
  {
    this.preferredPosition = preferredPosition;
  }

  /**
   * A method that will set the player status
   * @param status set the player status
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * A method that get player number
   * @return the player number
   */
  public int getPlayerNumber()
  {
    return playerNumber;
  }

  /**
   * A method that get the player first name
   * @return the player first name
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * A method that get the player last name
   * @return the player last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * A method that get the preferred position
   * @return the player preferred position
   */
  public String getPreferredPosition()
  {
    return preferredPosition;
  }

  /**
   * A method that get the player status
   * @return the player status
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * A method that will return a String with all the details of the Player
   * @return the player details
   */
  @Override public String toString()
  {
    return "Player" + "firstName='" + firstName + '\'' + ", lastName='"
        + lastName + '\'' + ", playerNumber=" + playerNumber
        + ", preferredPosition='" + preferredPosition + '\'' + ", status='"
        + status + '\'';
  }

  /**
   * A method that will compare if another object is equal with the current player
   * @param obj the other object that need to be verify if is same or not with the current player
   * @return true if everything is matching and false if something is not equal
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Player))
    {
      return false;
    }
    Player other = (Player) obj;

    if(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.playerNumber==other.playerNumber&&this.preferredPosition.equals(other.preferredPosition)&&this.status.equals(other.status)) return true;
    else return false;
  }
}






