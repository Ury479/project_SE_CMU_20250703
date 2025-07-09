import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取单词数量
        scanner.nextLine(); // 读取换行符

        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            if (word.length() > 10) {
                // 缩写：首字母 + (长度-2) + 尾字母
                System.out.println(word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1));
            } else {
                // 不变
                System.out.println(word);
            }
        }
        scanner.close();
    }
}
