package cn.objectOfStudent_2;

import java.io.*;
import java.util.*;

class Student {
    private String SID;
    private String firstName;
    private String lastName;

    public Student(String SID, String firstName, String lastName) {
        this.SID = SID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.SID + ", " + this.firstName + " " + this.lastName;
    }
}



