public class UniData implements Comparable<UniData> {
    
    String year, score, uniName, link, city, country, logo, rank;

    public UniData() {

    }

    public UniData(String year, String rank, String uniName, String  score, String link, String country, String city, String logo) {
        this.year = year;
        this.rank = rank;
        this.uniName = uniName;
        this.score = score;
        this.link = link;
        this.country = country;
        this.city = city;
        this.logo = logo;

    }

    public String  getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUniName() {
        return this.uniName;
    }

    public void setUniName(String x) {
        this.uniName = x;
    }

    public String getRank() {
        return this.rank;
    }

    public String  getScore() {
        return this.score;
    }

    public void setScore(String  x) {
        this.score = x;
    }

    public void setRank(String x) {
        this.rank = x;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String x) {
        this.link = x;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String x) {
        this.logo = x;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String x) {
        this.country = x;
    }

    public String getCity() {
        return this.country;
    }

    public void setCity(String x) {
        this.city = x;
    }

    public String toString() {
        return "[" + this.year + ", " + this.rank + ", " + this.uniName + ", " + this.score + ", " 
        + this.link + ", " + this.country + ", " + this.city + ", " + this.logo + "]";

    }

    @Override
    public int compareTo(UniData otherUniData){
        return this.uniName.compareTo(otherUniData.uniName);
    }
}
