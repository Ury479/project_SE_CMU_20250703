package assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**yihao wang 652115520*/
public class A2T {
	// Defines a public class named Main
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the address of file:");
		// Specifies the file name to read
		String fileName = input.nextLine();

		// Try block to execute code that might throw exceptions
		try {
			// Calls readFile method to read the file content and stores it in a String variable content
			String content = readFile(fileName);
			// Creates a TextAnalyzer object with the file content
			A2 analyser = new A2(fileName);
			// Calls the analyzeText method of the TextAnalyzer object to analyze the text
			analyser.analyzeText();
			// Catches IOException if an error occurs while reading the file and prints the error message
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
}
	
	public static String readFile(String fileName) {
	    StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	            contentBuilder.append(currentLine).append("\n");
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found: " + fileName);
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.out.println("Error reading file: " + fileName);
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}

}
