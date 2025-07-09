package T6_2;

/**652115520 YIHAO WANG*/
//thanapong

import java.io.*;
import java.util.*;


public class CovidVaccineData {
    private int SID;
    private String firstname;
    private String last;
    private String[] vaccinedata;
    private String date;

    //constructor
    public CovidVaccineData(int sID, String firstname, String last, String[] vaccinedata, String date) {
        SID = sID;
        this.firstname = firstname;
        this.last = last;
        this.vaccinedata = vaccinedata;
        this.date = date;
    }

    //getter setter method
    public int getSID() {
        return SID;
    }

    public void setSID(int sID) {
        SID = sID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String[] getVaccinedata() {
        return vaccinedata;
    }

    public void setVaccinedata(String[] vaccinedata) {
        this.vaccinedata = vaccinedata;
    }

    //toString method
    @Override
    public String toString() {
        return "CovidVaccineData [SID = " + SID + ", firstname = " + firstname + ", last = " + last + ", vaccinedata = "
                + Arrays.toString(vaccinedata) + ", Date = " + date +"]";
    }

    
}
