package assignment2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Defines a class named TextAnalyzer for analyzing text content
public class A2 {
	private final String content;
	// Constructor that initializes the TextAnalyzer with text content

	/**
	 * @param content
	 */
	public A2(String content) {
		super();
		this.content = content;
	}
	
	// Main method that orchestrates various text analysis functions
	public void analyzeText() {
	// Record start time for execution time calculation
		long startTime = System.currentTimeMillis();
		
		// Count total characters
		int totalCharacter = content.length();
		// Count palindromes
		int countPalindromes = countParlindormes();
	// Count tokens (words)
		int counTokens = counTokens();
	// Count emoticons
		int countEmotions = countEmotions();
	// Count new lines
		int countNewline = numOfnewLine();
	// Calculate longest and average token sizes
		double[] calSize = calAverageTokenSize();
	// Record end time
		long endTime = System.currentTimeMillis();
		// Calculate execution time in seconds
		double elapseTime = (endTime - startTime) /1000.0;
		// Print the results of the analysis
		printResult(totalCharacter, countPalindromes, counTokens, countEmotions, countNewline, calSize, elapseTime);
	}

	// Counts and returns the number of palindromes in the content
	public int countParlindormes() {
		String[] words = content.split("\\s+");
	// Split content into words
		int parlindromCount = 0;
	// Check if word is a palindrome
		for (String word : words) {
			if (isPalindromeWords(word)) {
				parlindromCount++;
			}
		}
		return parlindromCount;
	}
	
	// Checks if a word is a palindrome and returns true if it is
	public boolean isPalindromeWords(String word) {
	// Clean word by removing non-alphabetic characters
		String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
	// Word is not a palindrome
		int len = cleanWord.length();
	// Word is a palindrome
		for (int i = 0; i < len/2; i++) {
			if (cleanWord.charAt(i) != cleanWord.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
	// Counts and returns the number of tokens (words) in the content
public int counTokens() {
	// Split content into tokens
	String[] tokens = content.split("\\s+");
	return tokens.length;
}

	// Counts and returns the number of emoticons in the content
public int countEmotions() {
	// Regex pattern for simplistic emoticon detection
	Pattern pattern = Pattern.compile("(:\\)|:\\(|;\\)|:D)");
	Matcher matcher = pattern.matcher(content);
	int count = 0;
	while (matcher.find()) {
		count++;
	}
	return count;
}

	// Counts and returns the number of new lines in the content
private int numOfnewLine() {
	int count = content.split("\n",-1).length - 1;
	return count;
}

private double[] calAverageTokenSize() {
	// Split content by new line and count
	String[] tokens =content.split("\\s+");
	// Calculates and returns the longest and average token sizes
	int longest = 0;
	double totalLenth = 0;
	// Split content into tokens
	for (String token : tokens) {
		int length = token.length();
		longest = Math.max(longest,length);
		totalLenth += length;
	}
	double average = totalLenth / tokens.length;
	return new double[] {longest,average};
}
	// Prints the results of the text analysis
public void printResult(int totalCaracters,int countParlindormes,int counTokens,
		int countEmotions,int numOfnewLine,double[] calAverageTokenSize,double elapseTime) {
	 System.out.println("Program start:\n");
     System.out.println("Total Character count: " + totalCaracters);
     System.out.println("Total  Palindrome found: " +  countParlindormes);
     System.out.println("Total Number of tokens: " +  counTokens);
     System.out.println("Total Number of emoticon: " +  countEmotions);
     System.out.println("Total  of new line: " +  numOfnewLine);
     System.out.println("The longest token size: " + (int)calAverageTokenSize[0] + ", Average token size: " + calAverageTokenSize[1]);
     System.out.println("Total time to execute this program: " + elapseTime + "secs\n");
     System.out.println("Program terminated properly!");
	}
}
