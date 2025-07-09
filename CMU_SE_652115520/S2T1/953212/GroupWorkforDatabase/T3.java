package cn.T3;

import java.util.ArrayList;
import java.util.Scanner;
/**652115520 yihao wang*/
public class T3 {

    // Modify the generic parameters to ensure that the bubbling sort is applicable ArrayList<Integer>
    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Change the comparison condition to less than to achieve descending sorting
                if (list.get(j) > list.get(j + 1)) {
                    // Swap elements
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("$> Enter the numbers (or enter 'done' to finish): ");
            String line = input.nextLine();

            // Detect if the user has entered "done"
            if ("done".equalsIgnoreCase(line)) {
                break;
            }

            try {
                int num = Integer.parseInt(line);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer or 'done' to finish.");
            }
        }

        ArrayList<Integer> sortedNumbers = bubbleSort(numbers);
        System.out.println("$> Sorted array is: " + sortedNumbers);

        input.close();
    }
}
