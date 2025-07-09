package cn.objectOfStudent_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data {
	/**YIHAO WANG 652115520*/
    public static void main(String[] args) {
        /*
         * / Path to the CSV file. ："D:\\Dounload\\953102701000 (2).csv" is OK,
         * but "D:\\t1.csv" has no people ,"D:\\953102_701000.csv"is "No line found"
         */
        
    	String filePath =  "D:\\953102701000 (2).csv"; // Update with your actual file path.

        try {
            File f = new File(filePath);
            Scanner in = new Scanner(f);

            // Skip header lines. Adjust the number based on your actual CSV header lines.
            for (int i = 0; i < 7; i++) {
                in.nextLine();
            }

            List<Student> students = new ArrayList<>();

            while (in.hasNextLine()) {
                String dataLine = in.nextLine();
                // Assuming the CSV file is properly formatted as per the uploaded image.
                String[] dataParts = dataLine.split(",");
                // Assuming the format is ID, First Name, Last Name in the CSV.
                if (dataParts.length >= 3) {
                    String SID = dataParts[0].trim();
                    String firstName = dataParts[1].trim();
                    String lastName = dataParts[2].trim();
                    students.add(new Student(SID, firstName, lastName));
                }
            }

            // Display total number of students and their details.
            System.out.println("Total students: " + students.size());
            for (Student student : students) {
                System.out.println(student.toString());
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}