package day005;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**YIHAO WANG 652115520*/
public class A5 {
	// Use mySearch to find the count of values greater than 0.5
	public static int mySearch(double[]data) {
		// Method to count values greater than 0.5
		int count = 0;
		for (double valuie : data) {
			if (valuie > 0.5) {
				count++;
			}
		} 
		return count;
	}

		public static void main(String[] args) {
			
		try {
			// Replace this with the actual path to your data file
			File f = new File("D:\\download\\data.txt");
			Scanner sc = new Scanner(f);
			ArrayList<Object> list = new ArrayList<>();
			
			int number = 0;
			int totalValue = 0;
			// Read the file and store the values in a list
			while (sc.hasNext()) {
				// Convert the list to an array for mySearch method
				list.add(sc.nextDouble());
			}
			sc.close();
			double[] data = new double[list.size()];
			for (int i = 0; i < list.size(); i++) {
				data[i] = (double) list.get(i);
			}
			int dataThanHalf = mySearch(data);
			// Output the results
			System.out.println("Total number of values read: " + data.length);
            System.out.println("Number of value > 0.5 is: " + dataThanHalf);
			
		} catch (FileNotFoundException e) {
			// Handle the case where the file is not found
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
