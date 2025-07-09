package cn.readFileP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
/**652115520 YIHAO WANG */
public class ReadFileP {

    public ReadFileP(String string) {
        // This constructor is currently unused, it can be removed if not needed.
    }

    public static void main(String[] args) {
        // Assume a maximum of 100 rows
        final int MAX_ROWS = 100;
        double[] rowSums = new double[MAX_ROWS];
        int rowCount = 0;
        double totalSum = 0;

        try {
            // Use BufferedReader to read text files
            BufferedReader reader = new BufferedReader(new FileReader("\t1.csv"));
            String line;
            
            while ((line = reader.readLine()) != null && rowCount < MAX_ROWS) {
                String[] values = line.split(",");
                // Ensure each row has two data values
                if (values.length >= 2) {
                    double a = Double.parseDouble(values[0].trim());
                    double b = Double.parseDouble(values[1].trim());
                    double sum = a + b;
                    rowSums[rowCount++] = sum;
                    totalSum += sum;
                }
            }
            reader.close();

            // Sort the row sums array
            Arrays.sort(rowSums, 0, rowCount);

            // Print the top five row sums
            System.out.println("Top five of answer:");
            for (int i = rowCount - 1; i >= rowCount - 5 && i >= 0; i--) {
                System.out.println(rowSums[i]);
            }

            // Print the bottom five row sums
            System.out.println("Bottom five of answer:");
            for (int i = 0; i < 5 && i < rowCount; i++) {
                System.out.println(rowSums[i]);
            }

            // Print the total sum of all rows
            System.out.println("Sum of the rows = " + totalSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
