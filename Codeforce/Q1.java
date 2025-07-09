/* 
1.Recognize if the input data is even
if(nunber % 2 == 0),ture else false
 */
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        
        // Check if the watermelon can be divided into two parts with even weights
        if (canDivideIntoEvenParts(w)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
        scanner.close();
    }
    
    /*
     * Check if a watermelon with weight w can be divided into two parts with even weights
     * Conditions:
     * 1. w must be even
     * 2. w must be greater than or equal to 4 (because 2 cannot be divided into two positive even numbers)
     */
    public static boolean canDivideIntoEvenParts(int w) {
        // If w is odd, it cannot be divided into two even parts
        if (w % 2 != 0) {
            return false;
        }
        
        // If w is 2, it cannot be divided into two positive even parts
        if (w == 2) {
            return false;
        }
        
        // w is even and greater than or equal to 4, can be divided into two positive even parts
        return true;
    }
}