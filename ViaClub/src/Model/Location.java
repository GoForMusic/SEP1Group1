package Model;

public class Location {

    private String country;
    private String city;
    private String stadium;

    public Location(String country,String city, String stadium)
    {
        this.country = country;
        this.city = city;
        this.stadium = stadium;

    }
    public void setCountry(String country) throws Exception {
        if(country == "No country") {
            throw new Exception(country);
        }
    }
    public void setCity(String city) throws Exception {
        if(city == "No city")  {
            throw new Exception(city);
        }
    }
    public void setStadium(String stadium) throws Exception {
        if(stadium == "No stadium") {
            throw new Exception(stadium);
        }
    }
    public String getCountry()
    {
        return country;
    }
    public String getCity()
    {
        return city;
    }
    public String getStadium()
    {
        return stadium;
    }
    public String toString()
    {
        return "Country: "+ this.country + " City: " + this.city + " Stadium: " + this.stadium;
    }
    public Location copy()
    {
        return new Location(this.country,this.city,this.stadium);
    }
}
