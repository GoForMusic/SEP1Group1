package Model;

import java.io.Serializable;


/**
 * A class that include all the information about the location of a match
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class Location implements Serializable
{

    private String country;
    private String city;
    private String stadium;

    /**
     * A three-argument constructor with country,city and stadium
     * @param country country name where the match will be located
     * @param city city name where the match will be located
     * @param stadium stadium name where the match will be located
     */
    public Location(String country,String city, String stadium)
    {
        this.country = country;
        this.city = city;
        this.stadium = stadium;

    }

    /**
     * A method that will set the country name
     * @param country the new country name where the match will be located
     * @throws Exception will throw an exception if the country name is empty or null
     */
    public void setCountry(String country) throws Exception {
        if(country == null || country=="") {
            throw new Exception(country);
        }else{
            this.country=country;
        }
    }

    /**
     * A method that will set the city name
     * @param city the new city name where the match will be located
     * @throws Exception will throw an exception if the city name is empty or null
     */
    public void setCity(String city) throws Exception {
        if(city == ""||city==null)  {
            throw new Exception(city);
        }else{
            this.city=city;
        }
    }

    /**
     * A method that will set the stadium name
     * @param stadium the new stadium name where the match will be located
     * @throws Exception will throw an exception if the stadium name is empty or null
     */
    public void setStadium(String stadium) throws Exception {
        if(stadium == ""||stadium==null) {
            throw new Exception(stadium);
        }else{
            this.stadium=stadium;
        }
    }

    /**
     * This method will return the country name
     * @return country as a String type;
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * This method will return the city name
     * @return city as a String type
     */
    public String getCity()
    {
        return city;
    }

    /**
     * This method will return the stadium name
     * @return stadium as a String type
     */
    public String getStadium()
    {
        return stadium;
    }

    /**
     * A method that returns a String of all the information of location
     * @return all the information of location
     */
    public String toString()
    {
        return "Country: "+ this.country + " City: " + this.city + " Stadium: " + this.stadium;
    }

    /**
     * A method that will verify if another object is equal with Location
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj){
        if(!(obj instanceof Location)) return false;

        Location location = (Location) obj;

        if(this.city.equals(location.city)&&this.country.equals(location.country)&&this.stadium.equals(location.stadium)) return true;
        else return false;
    }

    /**
     * A method that returns a copy of location
     * @return copy of location
     */
    public Location copy()
    {
        return new Location(this.country,this.city,this.stadium);
    }
}
