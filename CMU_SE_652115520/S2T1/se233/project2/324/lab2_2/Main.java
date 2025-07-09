package lab2_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Demo> uniList = new ArrayList<>();
        System.out.println("Program start!");
        try (Scanner sc = new Scanner(new File("/Users/ury/Downloads/324/lab2_2/QS-World-University-Rankings-2017 copy.csv"))) {
            sc.useDelimiter(",");
            sc.nextLine(); // Skip header line

            while (sc.hasNextLine()) {
                String year = sc.hasNext() ? sc.next() : "";
                String rank = sc.hasNext() ? sc.next() : "";
                String uniName = sc.hasNext() ? sc.next() : "";
                String score = sc.hasNext() ? sc.next() : "";
                String link = sc.hasNext() ? sc.next() : "";
                String country = sc.hasNext() ? sc.next() : "";
                String city = sc.hasNext() ? sc.next() : "";
                String logo = sc.hasNext() ? sc.next() : "";

                // Convert university name to lowercase
                uniName = uniName.toLowerCase();

                // Replace empty score with "0" and round scores
                if (!isNumeric(score)) {
                    score = "0";
                }
                score = roundScore(score);

                // Add to list if rank is numeric
                if (isNumeric(rank)) {
                    Demo university = new Demo(year, rank, uniName, score, link, country, city, logo);
                    uniList.add(university);
                }

                if (sc.hasNextLine()) {
                    sc.nextLine(); // Move to the next line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort by university name
        Collections.sort(uniList);

        // Save to a new CSV file
        String csvFilePath = "/Users/ury/Downloads/324/lab2_2/QS-World-University-Rankings-2017 copy.csv";
        try (FileWriter fileWriter = new FileWriter(csvFilePath)) {
            fileWriter.write("Year,Rank,University Name,Score,Link,Country,City,Logo\n");
            for (Demo uni : uniList) {
                fileWriter.write(uni.getYear() + "," + uni.getRank() + "," + uni.getUniName() + "," + uni.getScore() +
                        "," + uni.getLink() + "," + uni.getCountry() + "," + uni.getCity() + "," + uni.getLogo() + "\n");
            }
            System.out.println("Data saved to " + csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print sorted universities
        for (Demo uni : uniList) {
            System.out.println(uni);
        }

        System.out.println("Program terminated properly!");
    }

    // Check if a string is numeric
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Round score to the nearest integer
    private static String roundScore(String str) {
        try {
            BigDecimal score = new BigDecimal(str);
            return score.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        } catch (NumberFormatException e) {
            return "0";
        }
    }
}
