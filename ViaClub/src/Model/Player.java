package Model;

import java.io.Serializable;

public class Player implements Serializable
{
  private String firstName;
  private String lastName;
  private int playerNumber;
  private String preferredPosition;
  private String status;

  public Player(String firstName, String lastName, int playerNumber,
      String preferredPosition, String status)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.playerNumber = playerNumber;
    this.preferredPosition = preferredPosition;
    this.status = status;
  }

  public void setPlayerNumber(int playerNumber)
  {
    this.playerNumber = playerNumber;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public void setPreferredPosition(String preferredPosition)
  {
    this.preferredPosition = preferredPosition;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public int getPlayerNumber()
  {
    return playerNumber;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPreferredPosition()
  {
    return preferredPosition;
  }

  public String getStatus()
  {
    return status;
  }

  @Override public String toString()
  {
    return "Player" + "firstName='" + firstName + '\'' + ", lastName='"
        + lastName + '\'' + ", playerNumber=" + playerNumber
        + ", preferredPosition='" + preferredPosition + '\'' + ", status='"
        + status + '\'';
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Player))
    {
      return false;
    }
    Player other = (Player) obj;

    return firstName.equals(other.firstName) && lastName.equals(other.lastName)
        && playerNumber == other.playerNumber && preferredPosition
        .equals(other.preferredPosition) && status.equals(other.status);
  }

  public Player copy()
  {
    return new Player(firstName, lastName, playerNumber, preferredPosition,
        status);
  }

}






