package lab2_2;

public class Demo implements Comparable<Demo> {
    private String year, rank, uniName, score, link, country, city, logo;

    public Demo() {}

    public Demo(String year, String rank, String uniName, String score, String link, String country, String city, String logo) {
        this.year = year;
        this.rank = rank;
        this.uniName = uniName;
        this.score = score;
        this.link = link;
        this.country = country;
        this.city = city;
        this.logo = logo;
    }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
    public String getUniName() { return uniName; }
    public void setUniName(String uniName) { this.uniName = uniName; }
    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    @Override
    public String toString() {
        return year + ", " + rank + ", " + uniName + ", " + score + ", " + link + ", " + country + ", " + city + ", " + logo;
    }

    @Override
    public int compareTo(Demo other) {
        return this.uniName.compareTo(other.uniName);
    }
}
